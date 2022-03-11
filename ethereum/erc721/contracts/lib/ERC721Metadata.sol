// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

/**
 * 元数据接口扩展，可选
 */
interface ERC721Metadata {
    /**
     * 名称
     */
    function name() external view returns (string memory _name);

    /**
     * 符号，建议全大写字母
     */
    function symbol() external view returns (string memory _symbol);

    /**
     * @dev Returns a distinct Uniform Resource Identifier (URI) for a given asset. It Throws if
     * `_tokenId` is not a valid NFT. URIs are defined in RFC3986. The URI may point to a JSON file
     * that conforms to the "ERC721 Metadata JSON Schema".
     * @return URI of _tokenId.
     */
    function tokenURI(uint256 _tokenId) external view returns (string memory);
}
