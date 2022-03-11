pragma solidity ^0.8.0;

import "./DispatcherStorage.sol";

contract Dispatcher {
    address private dsAddress = 0xd9145CCE52D386f254917e481eB44e9943F39138;

    fallback() external {
        DispatcherStorage ds = DispatcherStorage(dsAddress);
        address target = ds.libs("test");

        assembly {
            calldatacopy(0, 0, calldatasize())

            let result := delegatecall(gas(), target, 0, calldatasize(), 0, 0)

            //拷贝返回数据
            returndatacopy(0, 0, returndatasize())

            switch result
            case 0 {
                revert(0, returndatasize())
            }
            default {
                return(0, returndatasize())
            }
        }
    }
}
