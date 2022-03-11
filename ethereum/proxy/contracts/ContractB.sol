pragma solidity ^0.8.0;

import "./LibInterface.sol";

contract ContractB is LibInterface {
    uint256 public value;

    function get() public view override returns (uint256) {
        return value + 200;
    }

    function set(uint256 v) override public {
        value = v;
    }
}
