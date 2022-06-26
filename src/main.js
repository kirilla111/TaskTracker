import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'

const app = createApp(App).use(store).use(router).mount('#app');
// treat all tags starting with 'ion-' as custom elements


// app.config.compilerOptions.isCustomElement = tag => tag.startsWith('modal')



router.onError((error) => {
    const pattern = /Loading chunk (\d)+ failed/g;
    const isChunkLoadFailed = error.message.match(pattern);
    const targetPath = router.history.pending.fullPath;
    if (isChunkLoadFailed) {
    router.replace(targetPath);
    }
});
