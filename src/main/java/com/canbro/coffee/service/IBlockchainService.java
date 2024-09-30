package com.canbro.coffee.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canbro.coffee.controller.mng.vo.BlockchainRequest;
import com.canbro.coffee.pojo.entity.Blockchain;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author A-pppp
 * @since 2024-09-24
 */
public interface IBlockchainService {

    Page<Blockchain> page(BlockchainRequest blockchainRequest);

    boolean add(Blockchain blockchain);

    Blockchain detail(int blockchainId);

    boolean state(int blockchainId);

    boolean modify(Blockchain blockchain);
}
