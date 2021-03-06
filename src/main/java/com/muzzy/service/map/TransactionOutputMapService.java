package com.muzzy.service.map;

import com.muzzy.domain.TransactionOutput;
import com.muzzy.service.TransactionOutputService;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Paweł Mazur
 * 14.01.2020
 */
@Service
public class TransactionOutputMapService extends AbstractTransactionOutputMapService<TransactionOutput, String> implements TransactionOutputService {

    @Override
    public Set<TransactionOutput> getAll() {
        return super.findAll();
    }

    @Override
    public TransactionOutput getById(String id) {
        return super.findById(id);
    }

    @Override
    public TransactionOutput save(TransactionOutput t) {

        //    public void addTransaction(Transaction transaction, HashMap<String, TransactionOutput> map) {
//        if(transaction == null) return;
//        if((!previousHash.equals("0"))) {
//            if((!transaction.processTransaction(map))) {
//                System.out.println("Transaction failed to process. Discarded.");
//                return;
//            }
//        }
//        System.out.println("\n"+transaction.sender.toString().substring(40,194)+"\nis Attempting to send funds ("+transaction.value+") to "+"\n"+transaction.receiver.toString().substring(40,194)+"\n...");
//        transactions.add(transaction);
//        System.out.println("Transaction Successfully added to Block");
//    }
        return super.save(t);
    }

    @Override
    public void delete(TransactionOutput t) {
        super.delete(t);
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
    }

    @Override
    public Set<TransactionOutput> getTransctionByReciever(PublicKey publicKey) {
        Set<TransactionOutput> transactionOutputs = getAll();
        return transactionOutputs.stream().filter(txos -> txos.getReceiver().equals(publicKey)).collect(Collectors.toSet());
    }

    @Override
    public float getBalance(PublicKey publicKey) {
        Set<TransactionOutput> transactionOutputSet = this.getAll();
        double total = transactionOutputSet.stream()
                .filter(utxo -> utxo.isMine(publicKey))
                .collect(Collectors.summingDouble(TransactionOutput::getValue));
        return (float) total;
    }
}
