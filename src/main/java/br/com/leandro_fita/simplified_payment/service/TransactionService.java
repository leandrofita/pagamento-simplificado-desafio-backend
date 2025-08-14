package br.com.leandro_fita.simplified_payment.service;

import br.com.leandro_fita.simplified_payment.domain.*;
import br.com.leandro_fita.simplified_payment.domain.enums.OwnerType;
import br.com.leandro_fita.simplified_payment.mapper.TransactionMapper;
import br.com.leandro_fita.simplified_payment.model.transaction.TransactionCreateDTO;
import br.com.leandro_fita.simplified_payment.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private WalletOwnerRepository walletOwnerRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    public void processTransaction(TransactionCreateDTO dto) throws Exception {

        Transaction transaction = transactionMapper.toEntity(dto);

        recoverWalletsOrFail(dto, transaction);

        WalletOwner payerWalletOwner = transaction.getPayer().getOwner();
        WalletOwner payeeWalletOwner = transaction.getPayee().getOwner();

        applyBalanceValidations(payerWalletOwner, transaction);

        doMoneyTransfer(transaction);

        Transaction savedTransaction = transactionRepository.save(transaction);

        User payerUser = userRepository.findByWalletOwner_Id(payerWalletOwner.getId());

        if(payeeWalletOwner.getOwnerType().equals(OwnerType.STORE)){
            Store store = storeRepository.findByWalletOwner_Id(payeeWalletOwner.getId());
        } else {
            User payeeUser = userRepository.findByWalletOwner_Id(payeeWalletOwner.getId());
        }
    }

    private static void doMoneyTransfer(Transaction transaction) {
        Double currentBalence = transaction.getPayee().getBalance();

        transaction.getPayee().setBalance(currentBalence + transaction.getValue());
        transaction.getPayer().setBalance(transaction.getPayer().getBalance() - transaction.getValue());
    }

    private static void applyBalanceValidations(WalletOwner payerWalletOwner, Transaction transaction) throws Exception {
        if(payerWalletOwner.getOwnerType().equals(OwnerType.STORE)){
            throw new Exception("Transaction can't be made from store wallet");
        }
        if(transaction.getPayer().getBalance() < transaction.getValue()){
            throw new Exception("Payer wallet has not enough balance");
        }
    }

    private void recoverWalletsOrFail(TransactionCreateDTO dto, Transaction transaction) throws Exception {
        Optional<Wallet> payerWallet = walletRepository.findById(dto.getPayer());
        if(!payerWallet.isPresent()) throw new Exception("Payer wallet not found");
        transaction.setPayer(payerWallet.get());

        Optional<Wallet> payeeWallet = walletRepository.findById(dto.getPayer());
        if(!payeeWallet.isPresent()) throw new Exception("Payee wallet not found");
        transaction.setPayee(payeeWallet.get());
    }


}
