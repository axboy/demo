// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

/**
 * erc721 协议
 */
interface ERC721 {

    ///转账事件，NFT转移时触发
    ///创建NFT时，from == 0x0
    ///销毁NFT时，to == 0x0
    event Transfer(address indexed _from, address indexed _to, uint256 indexed _tokenId);

    //单个token授权事件
    event Approval(address indexed _owner, address indexed _approved, uint256 indexed _tokenId);

    //授权管理全部token
    event ApprovalForAll(address indexed _owner, address indexed _operator, bool _approved);

    //统计NFT数量
    function balanceOf(address _owner) external view returns (uint256);

    //显示NFT的owner
    function ownerOf(uint256 _tokenId) external view returns (address);

    ///授权/替换授权 NFT
    function approve(address _approved, uint256 _tokenId) external;

    ///授权操作全部NFT
    ///需支持授权多个
    function setApprovalForAll(address _operator, bool _approved) external;

    ///获取授权信息
    function getApproved(uint256 _tokenId) external view returns (address);

    ///查询完整授权
    function isApprovedForAll(address _owner, address _operator) external view returns (bool);

    ///NFT转移、转账
    //同transferFrom，额外检查_to地址，如果是合约地址，触发onERC721Received()回调
    function safeTransferFrom(address _from, address _to, uint256 _tokenId, bytes calldata _data) external;

    //重载方法
    function safeTransferFrom(address _from, address _to, uint256 _tokenId) external;

    //转账，调用方需要是主人、授权地址
    //不做_to地址校验，可能永久丢失NFT
    function transferFrom(address _from, address _to, uint256 _tokenId) external;
}
