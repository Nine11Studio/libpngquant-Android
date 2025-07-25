# libpngquant-Android
基于开源 pngquant 封装的一款压缩 PNG 图像的开源库，可用于 Android 平台或者其他使用 so 的场景。

它的主要优势在于：

1. 显著减小文件体积
压缩率高：pngquant 通常可以将 PNG 图像文件大小减小 50%-70%，有时甚至更多。
无明显画质损失：通过智能抖动和颜色量化算法，最大程度保留原始图像的视觉质量。

2. 支持 PNG 的 Alpha 通道（透明度）
与 JPEG 不同，PNG 支持透明背景，而 pngquant 在压缩时 保留透明信息，非常适合 UI 图标、App 素材、网页图像等。

3. 使用有损压缩实现视觉无损
它将 PNG 的 24/32 位图像转换为 8 位索引图（PNG8），并采用 Floyd-Steinberg 抖动，降低颜色深度的同时保留细节。

4. 兼容性好
生成的 PNG8 文件 完全符合 PNG 标准，可被所有主流浏览器、图像查看器和编辑器正常识别和使用。

5. 跨平台支持强
支持 命令行工具，也可以作为库集成（如 libquantquant），可在 Linux、macOS、Windows、Android 等平台使用。


相对于 TinyPng 依赖需要联网云端算法，Luban 不支持 png 压缩等，pngquant图片压缩可以 SDK 本地化、无需网络依赖，是目前压缩 png 最好的解决方案。


# 编译

使用 Cmake 直接编译源码生成 so，或者使用 gradlew assemble 编译工程生成 aar 使用。

# 其他库

该工程源码是基于以下开源库源码进行的整合，有兴趣可以了解

pngquant 
https://github.com/kornelski/pngquant

imagequant
https://github.com/ImageOptim/libimagequant

libpng
https://github.com/libpng/libpng

