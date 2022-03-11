// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "./lib/ERC721.sol";
import "./lib/ERC165.sol";
import "./lib/ERC721Enumerable.sol";
import "./lib/ERC721Metadata.sol";
import "./lib/ERC721TokenReceiver.sol";
import "./util/AddressUtil.sol";

contract FirstNFT is ERC721, ERC165, ERC721Enumerable, ERC721Metadata {
    //contract FirstNFT is ERC721, ERC165, ERC721Enumerable, ERC721Metadata, ERC721TokenReceiver {

    using AddressUtil for address;

    constructor(){
        //erc165
        _supportedInterfaces[0x01ffc9a7] = true;
    }


    ////// >>> ERC721

    struct NFT {
        uint id;
        address owner;
        address approved;
    }

    mapping(uint => NFT) private _data;
    uint private _dataSize;

    mapping(address => uint) private _count;

    mapping(address => mapping(address => bool)) _allowAll;

    /**
     * @dev Magic value of a smart contract that can receive NFT.
     * Equal to: bytes4(keccak256("onERC721Received(address,address,uint256,bytes)")).
     */
    bytes4 internal constant MAGIC_ON_ERC721_RECEIVED = 0x150b7a02;

    modifier canTransfer(uint id){
        NFT memory nft = _data[id];
        require(nft.owner == msg.sender ||
        nft.approved == msg.sender ||
            _allowAll[nft.owner][msg.sender],
            "Not owner or approved");
        _;
    }

    modifier canOperate(uint id){
        address owner = _data[id].owner;
        require(msg.sender == owner || _allowAll[owner][msg.sender], "Not owner or operator");
        _;
    }

    modifier validToken(uint id){
        require(id > 0 && _dataSize >= id, "invalid token id");
        require(_data[id].owner != address(0), "token destroyed");
        _;
    }

    //统计NFT数量
    function balanceOf(address _owner) external override view returns (uint256){
        return _count[_owner];
    }

    //显示NFT的owner
    function ownerOf(uint256 _tokenId) external override view validToken(_tokenId) returns (address){
        return _data[_tokenId].owner;
    }

    ///授权/替换授权 NFT
    function approve(address _approved, uint256 _tokenId) external override validToken(_tokenId) canOperate(_tokenId) {
        NFT storage nft = _data[_tokenId];
        require(_approved != nft.approved, "Approved already");
        nft.approved = _approved;
        emit Approval(nft.owner, _approved, _tokenId);
    }

    ///授权操作全部NFT
    ///需支持授权多个
    function setApprovalForAll(address _operator, bool _approved) external override {
        _allowAll[msg.sender][_operator] = _approved;
        emit ApprovalForAll(msg.sender, _operator, _approved);
    }

    ///获取授权信息
    function getApproved(uint256 _tokenId) external override view validToken(_tokenId) returns (address){
        return _data[_tokenId].approved;
    }

    ///查询完整授权
    function isApprovedForAll(address _owner, address _operator) external override view returns (bool){
        return _allowAll[_owner][_operator];
    }

    ///NFT转移、转账
    //同transferFrom，额外检查_to地址，如果是合约地址，触发onERC721Received()回调
    function safeTransferFrom(address _from, address _to, uint256 _tokenId, bytes calldata _msg) external override validToken(_tokenId) canTransfer(_tokenId) {
        _safeTransferFrom(_from, _to, _tokenId, _msg);
    }

    //重载方法
    function safeTransferFrom(address _from, address _to, uint256 _tokenId) external override validToken(_tokenId) canTransfer(_tokenId) {
        _safeTransferFrom(_from, _to, _tokenId, "");
    }

    //转账，调用方需要是主人、授权地址
    //不做_to地址校验，可能永久丢失NFT
    function transferFrom(address _from, address _to, uint256 _tokenId) validToken(_tokenId) canTransfer(_tokenId) external override {
        _transfer(_from, _to, _tokenId);
    }

    function _safeTransferFrom(address _from, address _to, uint256 _tokenId, bytes memory _msg) internal {
        require(_to != address(0), "zero address");
        _transfer(_from, _to, _tokenId);
        if (!_to.isContract()) {
            return;
        }
        bytes4 resVal = ERC721TokenReceiver(_to).onERC721Received(msg.sender, _from, _tokenId, _msg);
        require(resVal == MAGIC_ON_ERC721_RECEIVED, "disable to receive nft");
    }

    function _transfer(address _from, address _to, uint _tokenId) internal {
        _data[_tokenId].owner = _to;
        _data[_tokenId].approved = address(0);
        _count[_from] -= 1;
        _count[_to] += 1;
        emit Transfer(_from, _to, _tokenId);
    }
    ////// <<< ERC721

    ////// >>> ERC165
    mapping(bytes4 => bool) private _supportedInterfaces;

    function supportsInterface(bytes4 interfaceID) external override view returns (bool){
        return _supportedInterfaces[interfaceID];
    }
    ////// <<< ERC165

    ////// >>> ERC721Enumerable
    function totalSupply() external override view returns (uint256){
        return _dataSize;
    }

    function tokenByIndex(uint256 _index) external override pure returns (uint256){
        return _index;
    }

    function tokenOfOwnerByIndex(address _owner, uint256 _index) external override view returns (uint256){
        require(_owner == msg.sender, "unsupport");
        return _index;
    }

    ////// <<< ERC721Enumerable


    ////// >>> ERC721Metadata
    function name() external override pure returns (string memory _name){
        return "FirstNFT";
    }

    function symbol() external override pure returns (string memory _symbol){
        return "FIR";
    }

    function tokenURI(uint256 _tokenId) external override pure returns (string memory){
        require(_tokenId >= 0, "bad token");
        return "http://q.axboy.cn/json/nft.json";
    }
    ////// <<< ERC721Metadata

    function mint() external returns (uint){
        _dataSize += 1;
        NFT memory nft = NFT({id : _dataSize, owner : msg.sender, approved : address(0)});
        _data[_dataSize] = nft;
        emit Transfer(address(0), msg.sender, nft.id);

        _count[msg.sender] += 1;

        return _dataSize;
    }
}