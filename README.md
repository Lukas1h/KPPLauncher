# KPPLauncher
[KPPLauncher](https://github.com/lukas1h/KPPLauncher) is a MRPI package and KUAL extension that allows `react-native-kindle` apps to be ran directly from the library, similer to the now unsuportted `kindlets`.

## Instalation
1. Grab the newest release. 
2. Drop the `Update_KPPLBooklet__install.bin` in your `mrpackages` folder on your Kindle
3. Drop the KPPLauncher folder into your `extensions` folder (also on your Kindle).
4. Run `;log mrpi` in the search bar, or use KUAL to install the package.

## Compatibility
Should work on anything above `v5.15.1`.

`KPPLauncher` uses the obfusticated `Booklet` API and code from [KPVBooklet](https://github.com/koreader/kpvbooklet), which apparently doesn't work on newer frameware versions. That being said, it works on my device just fine, KP3 `v5.15.1`.


## Credits
- [KPVBooklet](https://github.com/koreader/kpvbooklet)
- [KOL](https://github.com/yparitcher/KUAL_Booklet)
