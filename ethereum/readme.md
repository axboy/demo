# Ethereum

### [Remix](https://remix.ethereum.org)

- [remix-ide](https://github.com/ethereum/remix-ide)

    ```sh
    docker run -d --name remix-ide -p 8080:80 remixproject/remix-ide:remix_live
    ```

- 链接本地文件

    ```sh
    npm install -g @remix-project/remixd
    remixd -s ./remix-work --remix-ide https://remix.ethereum.org
    ```

### [Truffle](https://www.trufflesuite.com/truffle)

### Idea配置

1. 下载[ethereum/solidity](https://github.com/ethereum/solidity)

2. idea打开 File/Settings/Tools/External Tools

    ```text
    name: Solidity-0.8.6
    Program: C:\Soft\Solidity\solidity-windows-0.8.6\solc.exe
    Arguments: --abi --bin $FileName$ -o $OutputPath$ --overwrite
    Working directory: $FileDir$
    ``` 

### 常用链接

- [etherscan.io](https://etherscan.io/)
- [infura.io](https://infura.io/)
- [币安链文档](https://docs.bscscan.com/)
- [领币安链测试币](https://testnet.binance.org/faucet-smart)
- [币安测试链，区块浏览器](https://testnet.bscscan.com/)
- [Ganache](https://www.trufflesuite.com/ganache)
- [Solidity入门教学视频](https://www.bilibili.com/video/BV1St411a7Pk)
- [Truffle中文文档](https://learnblockchain.cn/docs/truffle/quickstart.html)
