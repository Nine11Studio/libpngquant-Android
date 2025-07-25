cmake_minimum_required(VERSION 3.10)

project(pngquant_android)

# 设置编译为静态库还是共享库
set(BUILD_SHARED_LIBS ON)

# C 标准
set(CMAKE_C_STANDARD 99)

# 添加子目录或包含路径
include_directories(
        ${CMAKE_SOURCE_DIR}/libimagequant
        ${CMAKE_SOURCE_DIR}/libpng
)

set(libpng_arm_sources
        libpng/arm/arm_init.c
        libpng/arm/filter_neon_intrinsics.c
        libpng/arm/palette_neon_intrinsics.c
)

set(libpng_sources
        libpng/png.c
        libpng/pngerror.c
        libpng/pngget.c
        libpng/pngmem.c
        libpng/pngpread.c
        libpng/pngread.c
        libpng/pngrio.c
        libpng/pngrtran.c
        libpng/pngrutil.c
        libpng/pngset.c
        libpng/pngtrans.c
        libpng/pngwio.c
        libpng/pngwrite.c
        libpng/pngwtran.c
        libpng/pngwutil.c
)

set(libimagequant_sources
        libimagequant/libimagequant.c
        libimagequant/libimagequant.c
        libimagequant/blur.c
        libimagequant/mediancut.c
        libimagequant/mempool.c
        libimagequant/nearest.c
        libimagequant/pam.c
        libimagequant/kmeans.c
        libimagequant/remap.c
        ${libpng_sources}
        ${libpng_arm_sources}
)

# 添加源文件（你也可以使用 file(GLOB ...) 自动添加）
set(LIBPNGQUANT_SOURCE_FILES
        pngquant.c
        pngquant_opts.c
        rwpng.c
        pngquant_bridge.cpp
        ${libpng_sources}
        ${libpng_arm_sources}
        ${libimagequant_sources}
)

# 创建共享库供 JNI 使用
add_library(pngquant SHARED ${LIBPNGQUANT_SOURCE_FILES})

# 设置链接库（NDK 中需要链接 zlib）
find_library(log-lib log)
find_library(z-lib z)

target_link_libraries(pngquant
        ${log-lib}
        ${z-lib}
)
