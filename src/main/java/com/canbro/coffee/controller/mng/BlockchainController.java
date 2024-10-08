package com.canbro.coffee.controller.mng;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canbro.coffee.consts.HttpStatus;
import com.canbro.coffee.controller.mng.vo.BlockchainRequest;
import com.canbro.coffee.pojo.entity.Blockchain;
import com.canbro.coffee.pojo.vo.RetVo;
import com.canbro.coffee.service.IBlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  后台区块链模块
 * </p>
 *
 * @author A-pppp
 * @since 2024-09-24
 */
@RestController
@RequestMapping("/mng/blockchain")
public class BlockchainController {

    @Autowired
    private IBlockchainService blockchainService;

    @PostMapping("/page")
    public RetVo page(@RequestBody BlockchainRequest blockchainRequest) {

        if (blockchainRequest==null){
            return RetVo.failed();
        }

        Page<Blockchain> result = blockchainService.page(blockchainRequest);

        return RetVo.success(result);
    }

    @PostMapping("/add")
    public RetVo add(@RequestBody Blockchain blockchain) {
        boolean result = blockchainService.add(blockchain);
        if (result){
            return RetVo.success();
        }else {
            return RetVo.failed();
        }
    }

    @PostMapping("/modify")
    public RetVo modify(@RequestBody Blockchain blockchain) {
        boolean result = blockchainService.modify(blockchain);
        if (result){
            return RetVo.success();
        }else {
            return RetVo.failed();
        }
    }

    @GetMapping("/detail/{blockchainId}")
    public RetVo detail(@PathVariable("blockchainId") int blockchainId) {
        Blockchain blockchain = blockchainService.detail(blockchainId);
        if (blockchain==null){
            return RetVo.failed();
        }
        return RetVo.success(blockchain);
    }

    @PostMapping("/state/{blockchainId}")
    public RetVo state(@PathVariable("blockchainId") int blockchainId) {
        boolean update = blockchainService.state(blockchainId);
        if (update){
            return RetVo.success();
        }else {
            return RetVo.failed();
        }
    }
}
