package com.muzzy.service.map;

import com.muzzy.domain.Block;
import com.muzzy.domain.Transaction;
import com.muzzy.service.BlockService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BlockMapService extends AbstractBlockMapService<Block, String> implements BlockService {
    @Override
    public LinkedHashSet<Block> getAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(String id) {
    }

    @Override
    public void delete(Block t) {
    }

    @Override
    public Block save(Block t) {
        return super.save(t);
    }

    @Override
    public Block getById(String id) {
        return super.findById(id);
    }

    @Override
    public Block getLastBlock() {
        return super.getLastBlock();
    }

    @Override
    public Transaction getTransactionFromBlockById(String id) {
        if (id != null) {
            return this.getAll().stream().map(block -> block.getTransactions().stream()
                    .filter(transaction -> id.equals(transaction.getTransactionId()))
                    .findFirst()
                    .orElse(null)).filter(Objects::nonNull).findFirst().orElse(null);
        }
        return null;
    }

    @Override
    public Block getBlockWithTransaction(String id) {
        if (id != null) {
//            this.getAll().stream().forEach(block -> block.getTransactions().stream().filter());
            for (Block block : this.getAll()) {
                for (Transaction transaction : block.getTransactions()
                     ) {
                    if(id.equals(transaction.getTransactionId())){
                        return block;
                    }
                }
            }
        }
        return null;
    }
}