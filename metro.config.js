const { getDefaultConfig } = require('@expo/metro-config');

module.exports = (async () => {
  const config = await getDefaultConfig(__dirname);

  // Customize the config to disable Hot Module Replacement (Fast Refresh)
  config.transformer = {
    ...config.transformer,
    enableBabelRCLookup: false,
    enableBabelRuntime: false,
  };

  config.resolver = {
    ...config.resolver,
    sourceExts: [...config.resolver.sourceExts, 'cjs'],
  };

  config.server = {
    ...config.server,
    enhanceMiddleware: (middleware, server) => {
      // Disable hot module replacement (HMR)
      const originalSend = server._send;
      server._send = function (req, res, ...args) {
        if (req.url.includes('hot-update.json')) {
          res.writeHead(404);
          res.end();
          return;
        }
        originalSend.apply(this, [req, res, ...args]);
      };
      return middleware;
    },
  };

  return config;
})();
