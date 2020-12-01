# React Native using shadow-cljs in 3 minutes

The fastest way a [ClojureScript](https://clojurescript.org/) coder can get started with React Native development. *Prove me wrong.*

This is an example project, only slightly beyond *Hello World*, using: [shadow-cljs](https://github.com/thheller/shadow-cljs), [React Native](https://facebook.github.io/react-native/), [Expo](https://expo.io/), [Reagent](https://reagent-project.github.io/), and [re-frame](https://github.com/Day8/re-frame).

<img src="./rn-rf-shadow.png" width="320" />

Follow along to get started using [Calva](http://calva.io), [Emacs/CIDER](https://cider.mx), or the command line. It is assumed you have Java installad as well as dev tool chains for the platforms yuo are targeting. (If you are targeting the Web, then Chrome is enough.)

## Using Calva

Open the project in VS Code. Then:

1. `npm i`
1. Run the Calva command **Start a Project REPL and Connect (aka Jack-in)**
   1. Select the project type `Hello RN Shadow`.
   1. Wait for shadow to build the project.
1. Then **Start build task**. This will start Expo and the Metro
   builder. Wait for it to fire up Expo DevTools in your browser.
   1. Click **Run in web browser**
1. When the app is running the Calva CLJS REPL can be used. Confirm this by evaluating something like: 
   ``` clojure
   (js/alert "hello world!")
   ```
   (You should see the alert pop up where the app is running.)
1. Hack away!

Of course you should try to fire up the app on all simulators, emulators and phones you have as well. The Expo UI makes this really easy.

## Disabling Live and Hot Reload

See below about disabling the Hot and Live reload provided by the Expo client, which would defeat the shadow-cljs hot reloading. Shadow is way better and way faster than the Expo stuff is.

For the iOS and Android you do this by disabling Hot Reload from the Expo client [development menu](https://docs.expo.io/workflow/debugging/#developer-menu).

For the web app there is, afaik, no way to disable the Live Reload, but you can block it. neupsh@clojurians suggested the following:

> you can't turn off the live reload, but you can workaround it
> you can block requests to /sockjs-node/* in chrome (didn't work on firefox)

1. Open Developer Tools
2. Click the three dots
3. More tools > Request blocking
4. Click the `+`
5. Enter `*/sockjs-node/*`
6. Done!

![Step 1-3](https://memset.se/9429/e0f0c065c9c0231d80681ca7da72bbcd4a67ff1e)

![Step 4-6](https://memset.se/9430/4bd73bd45cda2f096a2d2106d22ba8130b0c5bd2)

## Using Emacs with CIDER

Open Emacs and a bash shell:

1. In the shell, execute `npm install -g expo-cli shadow-cljs`
1. Then `yarn` and wait for it to finish.
1. Then run `shadow-cljs compile :app` to perform an initial build of the app.
1. In Emacs open one of the files in the project (`deps.edn` is fine)
1. From that buffer, do `cider-jack-in-clojurescript` [C-c M-J] to
   launch a REPL. Follow the series of interactive prompts in the
   minibuffer:
   1. select `shadow-cljs` as the command to launch
   1. select `shadow` as the repl type
   1. select `:app` as the build to connect
   1. and optionally answer `y` or `n` to the final question about
      opening the `shadow-cljs` UI in a browser.
   At this point `shadow-cljs` will be watching the project folder and
   running new builds of the app if any files are changed. You'll also
   have a REPL prompt, *however the REPL doesn't work because it isn't
   connected to anything. The app isn't running yet.*
1. In a shell run `yarn ios` (same as `expo start -i`). This starts
   the Metro bundler, perform the bundling, launch the iPhone
   simulator, and transmit the bundled app. Be patient at this step as
   it can take many seconds to complete. When the app is finally
   running expo will display the message:
   
       WebSocket connected!
       REPL init successful
1. Once you see that the REPL is initalized, you can return to Emacs
   and confirm the REPL is connected and functional:
   ``` clojure
   cljs.user> (js/alert "hello world!")
   ```   
   Which should pop-up a modal alert in the simulator, confirming the
   app is running and the REPL is connected end to end.

## Or the Command line
```sh
$ npm i
$ npx shadow-cljs watch app
# wait for first compile to finish or expo gets confused 
# on another terminal tab/window:
$ npm start
```
This will run Expo DevTools at http://localhost:19002/

To run the app in browser using expo-web (react-native-web), press `w` in the same terminal after expo devtools is started.
This should open the app automatically on your browser after the web version is built. If it does not open automatically, open http://localhost:19006/ manually on your browser.

Note that you can also run the following instead of `yarn start` to run the app in browser:
   ```
   # same as expo start --web
   $ yarn web
   
   # or
   
   # same as expo start --web-only
   $ yarn web-only
   ```
Then use your editor of choice to hook up the REPL and such.


### Using ClojureScript REPL
Once the app is deployed and opened in phone/simulator/emulator/browser, connect to the nrepl on port 9000 and run the following:
```clojure
(shadow/nrepl-select :app)
```

```clojure
(js/alert "Hello from Repl")
```

## Production builds

A production build invloves first asking shadow-cljs to build a relase, then to ask Expo to work in Production Mode.

1. Kill the watch and expo tasks.
1. Execute `shadow-cljs release app`
1. Start the expo task (as per above)
   1. Enable Production mode.
   1. Start the app.

## "Known good" toolchain configurations

This repository provides a baseline setup for a React Native application. However newcomers may still have problems getting up in running "in 3 minutes" because of obscure dependencies on supporting tools such as the Java and Node runtimes. So while we can't definitively show every viable configuration, we can at least maintain what's known to work, especially when dependencies are bumped.

Expo SDK          | 35                | 35
----------------- | ----------------- | -----------------
clojure           | 1.10.1            | 1.10.1
clojurescript     | 1.10.520          | **1.10.597**
expo-cli          | 3.4.1             | **3.9.1**
expo              | 35.0.0            | 35.0.0
jdk               | openjdk 1.8.0_222 | openjdk 1.8.0_222
node              | 10.17.0           | 10.17.0
re-frame          | 0.11.0-rc2        | **0.11.0-rc3**
react             | 16.9.0            | 16.9.0
react-native      | 0.59.8            | 0.59.8
reagent           | 0.9.0-rc2         | **0.9.0-rc3**
shadow-cljs (cli) | 2.8.69            | **2.8.78**
shadow-cljs (jar) | 2.8.69            | **2.8.78**
yarn              | 1.19.1            | 1.19.1

When in doubt, a script is provided in this repo (`etc/toolchain-report`) to query what versions you have. This script is NOT needed for app development, building, or releasing, but may come in handy if you're having trouble getting up and running. `toolchain-report` requires `joker`, a portable and fast dialect of clojure implemented in go. See [the joker repo on github](https://github.com/candid82/joker) for installation instructions.

## Some notes from Thomas Heller

(This project is built from this example of his: https://github.com/thheller/reagent-expo)

The `:app` build will create an `app/index.js`. In `release` mode that is the only file needed. In dev mode the `app` directory will contain many more `.js` files.

`:init-fn` is called after all files are loaded and in the case of `expo` must render something synchronously as it will otherwise complain about a missing root component. The `shadow.expo/render-root` takes care of registration and setup.


Source maps don't seem to work properly. `metro` propably doesn't read input source maps when converting sources as things are correctly mapped to the source .js files but not their sources.

Initial load in dev is quite slow since `metro` processes the generated `.js` files.

`reagent.core` loads `reagent.dom` which will load `react-dom` which we don't have or need. Including the `src/main/reagent/dom.cljs` to create an empty shell. Copied from [re-natal](https://github.com/drapanjanas/re-natal/blob/master/resources/cljs-reagent6/reagent_dom.cljs).
