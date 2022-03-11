pragma solidity ^0.8.0;

contract DispatcherStorage {
    address public owner;

    constructor() {
        owner = msg.sender;
    }

    mapping(string => address) public libs;

    function replace(string calldata name, address libAddress) public {
        require(owner == msg.sender, "only owner");
        libs[name] = libAddress;
    }
}
