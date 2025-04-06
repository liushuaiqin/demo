const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  devServer: {
    port: 8080,
    client: {
      overlay: false
    },
    proxy: {
      '/api': {
        target: 'http://localhost:8090',
        changeOrigin: true,
        pathRewrite: { '^/api': '' }
      }
    }
  },
})
