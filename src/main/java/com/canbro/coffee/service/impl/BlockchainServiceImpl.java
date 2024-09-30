package com.canbro.coffee.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canbro.coffee.controller.mng.vo.BlockchainRequest;
import com.canbro.coffee.pojo.entity.Blockchain;
import com.canbro.coffee.mapper.BlockchainMapper;
import com.canbro.coffee.service.IBlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author A-pppp
 * @since 2024-09-24
 */
@Service
public class BlockchainServiceImpl implements IBlockchainService {

    @Autowired
    private BlockchainMapper blockchainMapper;

    @Override
    public Page<Blockchain> page(BlockchainRequest blockchainRequest) {

        String blockchainCode = blockchainRequest.getBlockchainCode();
        String blockchainName = blockchainRequest.getBlockchainName();
        int blockchainState = blockchainRequest.getBlockchainState();
        int page = blockchainRequest.getPage()-1;
        int size = blockchainRequest.getSize();
        // 构建查询条件
        QueryWrapper<Blockchain> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(blockchainCode)){
            queryWrapper.eq("blockchain_code", blockchainCode);
        }
        if (StrUtil.isNotBlank(blockchainName)){
            queryWrapper.eq("blockchain_name", blockchainName);
        }
        if (blockchainState>=0){
            queryWrapper.eq("blockchain_state", blockchainState);
        }
        Long l = blockchainMapper.selectCount(queryWrapper);
        Page<Blockchain> blockchainPage = new Page<>(page, size, l,true);

        return blockchainMapper.selectPage(blockchainPage, queryWrapper);

    }

    @Override
    public boolean add(Blockchain blockchain) {

        blockchain.setCreateTime(Long.parseLong(DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN)));
        blockchain.setBlockchainState(0);

        int i = blockchainMapper.insert(blockchain);

        return i>0;
    }

    @Override
    public Blockchain detail(int blockchainId) {

        if (blockchainId<=0){
            return null;
        }

        QueryWrapper<Blockchain> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("blockchain_id", blockchainId);
        return blockchainMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean state(int blockchainId) {
        if (blockchainId<=0){
            return false;
        }
        QueryWrapper<Blockchain> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("blockchain_id", blockchainId);
        Blockchain blockchain = blockchainMapper.selectOne(queryWrapper);
        if (blockchain==null){
            return false;
        }

        Integer state = blockchain.getBlockchainState();
        int modifyState = switch (state) {
            case 0, 2 -> 1;
            case 1 -> 2;
            default -> 0;
        };
        blockchain.setBlockchainState(modifyState);
        blockchain.setModifyTime(Long.parseLong(DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN)));
        UpdateWrapper<Blockchain> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("blockchain_id", blockchainId);

        int update = blockchainMapper.update(blockchain, updateWrapper);

        return update>0;
    }

    @Override
    public boolean modify(Blockchain blockchain) {

        if (blockchain==null || blockchain.getBlockchainId()<=0){
            return false;
        }
        blockchain.setModifyTime(Long.parseLong(DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN)));
        UpdateWrapper<Blockchain> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("blockchain_id", blockchain.getBlockchainId());
        int update = blockchainMapper.update(blockchain, updateWrapper);

        return update>0;
    }
}
