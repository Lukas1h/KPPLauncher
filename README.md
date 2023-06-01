# KPPLauncher
[KPPLauncher](https://github.com/lukas1h/KPPLauncher) is a MRPI package and KUAL extension that allows [react-native-kindle](https://github.com/Lukas1h/react-native-kindle) apps to be ran directly from the library, similer to the now unsuportted `kindlets`.


<img src="https://github.com/Lukas1h/KPPLauncher/assets/53445584/0e52cd39-6280-448e-bc43-d52ebe2775fd" width="22%">
<img src="https://github.com/Lukas1h/KPPLauncher/assets/53445584/4ffb3a18-4dc3-4449-b064-491d39e33f3d" width="22%">
<img src="https://github.com/Lukas1h/KPPLauncher/assets/53445584/35f5db57-b97a-47ba-95dc-72abd7515917" width="22%">
<img src="https://github.com/Lukas1h/KPPLauncher/assets/53445584/876154c8-4fac-4bd7-b2ab-807a4d91a841" width="22%">

## Instalation
1. Grab the newest release. 
2. Drop the `Update_KPPLBooklet__install.bin` in your `mrpackages` folder on your Kindle
3. Run `;log mrpi` in the search bar, or use KUAL to install the package.

## Compatibility
Should work on anything above `v5.13.7`, as thats when React Native was released. 

`KPPLauncher` uses the obfusticated `Booklet` API and code from [KPVBooklet](https://github.com/koreader/kpvbooklet), which apparently doesn't work on newer frameware versions. That being said, it works on my device just fine, PW3 `v5.15.1` and `v5.14.2`.

### Tested on:
- PW3 on `v5.14.2` *thanks, martinpham*
- PW3 on `v5.15.1`
 
## Applications
- [💣 Minesweeper](https://github.com/Lukas1h/react-native-kindle-minesweeper)
- [♟️ Chess](https://github.com/Lukas1h/react-native-chess)
- Open a issue if you'd like your application to be added.

## Credits
- [KPVBooklet](https://github.com/koreader/kpvbooklet)
- [KOL](https://github.com/yparitcher/KUAL_Booklet)
