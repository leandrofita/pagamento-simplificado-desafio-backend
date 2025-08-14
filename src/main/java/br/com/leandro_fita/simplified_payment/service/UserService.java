package br.com.leandro_fita.simplified_payment.service;

import br.com.leandro_fita.simplified_payment.domain.User;
import br.com.leandro_fita.simplified_payment.domain.Wallet;
import br.com.leandro_fita.simplified_payment.domain.WalletOwner;
import br.com.leandro_fita.simplified_payment.domain.enums.OwnerType;
import br.com.leandro_fita.simplified_payment.mapper.UserMapper;
import br.com.leandro_fita.simplified_payment.model.user.UserCreateDTO;
import br.com.leandro_fita.simplified_payment.model.user.UserResponseDTO;
import br.com.leandro_fita.simplified_payment.repository.UserRepository;
import br.com.leandro_fita.simplified_payment.repository.WalletOwnerRepository;
import br.com.leandro_fita.simplified_payment.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WalletOwnerRepository walletOwnerRepository;

    @Autowired
    private WalletRepository walletRepository;



    public UserResponseDTO save(UserCreateDTO dto) {

        throwExceptionCaseUserIsFoundByEmailOrCPF(dto);

        WalletOwner savedWalletOwner = createWalletOwner();

        Wallet wallet = createWallet(savedWalletOwner);

        User user = userMapper.toEntity(dto);
        user.setWalletOwner(savedWalletOwner);

        UserResponseDTO responseDTO = userMapper.toResponseDTO(saveUserOrFail(user));
        responseDTO.setWalletId(wallet.getId());

        return responseDTO;

    }

    private User saveUserOrFail(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível salvar o usuário", e);
        }
    }

    private Wallet createWallet(WalletOwner savedWalletOwner) {
        Wallet wallet = new Wallet();
        wallet.setOwner(savedWalletOwner);
        wallet.setBalance(0.0);
        return walletRepository.save(wallet);
    }

    private WalletOwner createWalletOwner() {
        WalletOwner walletOwner = new WalletOwner();
        walletOwner.setOwnerType(OwnerType.USER);
        return walletOwnerRepository.save(walletOwner);
    }

    private void throwExceptionCaseUserIsFoundByEmailOrCPF(UserCreateDTO user) {
        Optional<User> userFoudnByEmail = userRepository.findByEmail(user.getEmail());
        if (userFoudnByEmail.isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        Optional<User> userFoudnByCpf = userRepository.findByCpf(user.getCpf());
        if (userFoudnByCpf.isPresent()) {
            throw new RuntimeException("Cpf já cadastrado");
        }
    }

}
