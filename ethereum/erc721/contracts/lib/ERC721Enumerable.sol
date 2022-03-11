// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

/**
 * 枚举接口扩展，可选
 */
interface ERC721Enumerable {
    /**
     * token总数量
     */
    function totalSupply() external view returns (uint256);

    /**
     * 根据编号查token
     */
    function tokenByIndex(uint256 _index) external view returns (uint256);

    /**
     * 查询_owner第_index个token
     */
    function tokenOfOwnerByIndex(address _owner, uint256 _index) external view returns (uint256);
}
