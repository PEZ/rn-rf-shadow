// Disable all Expo/Metro hot reload mechanisms BEFORE loading app
// This ensures only shadow-cljs hot reload remains active
if (typeof window !== 'undefined') {
  // Disable React Fast Refresh
  window.$RefreshReg$ = () => {};
  window.$RefreshSig$ = () => type => type;
  
  // Disable Metro hot update
  window.metroHotUpdateModule = () => {};
  window.__accept = () => {};
  
  // Disable webpack hot update (fallback)
  window.webpackHotUpdate = () => {};
  
  // Override WebSocket to block Metro connections
  // const OriginalWebSocket = window.WebSocket;
  // window.WebSocket = function(url) {
  //   if (url && (url.includes('metro') ||
  //               url.includes('packager') ||
  //               url.includes('19001') ||
  //               url.includes('19000') ||
  //               url.includes('8081'))) {
  //     console.log('Blocked Metro WebSocket connection:', url);
  //     return {
  //       send: () => {},
  //       close: () => {},
  //       addEventListener: () => {},
  //       removeEventListener: () => {}
  //     };
  //   }
  //   return new OriginalWebSocket(url);
  // };
  
  // Prevent module.hot usage
  if (typeof module !== 'undefined' && module.hot) {
    delete module.hot;
  }
  
  console.log('Expo/Metro hot reload disabled - shadow-cljs will handle hot reload');
}

import './app/index.js';