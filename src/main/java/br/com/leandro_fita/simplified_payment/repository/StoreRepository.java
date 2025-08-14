package br.com.leandro_fita.simplified_payment.repository;

import br.com.leandro_fita.simplified_payment.domain.Store;

public interface StoreRepository {

    Store findByWalletOwner_Id(Long id);
}
