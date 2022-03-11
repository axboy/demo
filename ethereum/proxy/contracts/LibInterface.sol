pragma solidity ^0.8.0;

//定义接口
interface LibInterface {
    function get() external view returns (uint256);

    function set(uint256 v) external;
}
