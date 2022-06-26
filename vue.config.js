module.exports = {
    configureWebpack: {
    resolve: {
        fallback: {
            "url": require.resolve("url/"),
            "path": require.resolve("path-browserify"),
            "zlib": require.resolve("browserify-zlib"),
            "path": false,
            "util": require.resolve("util/"),
            "stream": require.resolve("stream-browserify"),
            "http": require.resolve("stream-http"),
            "crypto": require.resolve("crypto-browserify"),
            "assert": require.resolve("assert/"),
            "fs": false,
            "net": false
        }
    }}
}