# Studio_Submodule
工作室通用模块

1. 每次提交代码，如果改动到旧的代码，请和其他人确认兼容性
2. 每次提交代码之前，必须自测，保证可以run起来


# 子模块的添加
git submodule add <url> <path>

url为子模块的路径，path为该子模块存储的目录路径。

进入子模块目录，pull一下。

# 子模块的更新

进入子模块目录，pull一下。


# 删除子模块

rm -rf 子模块目录 删除子模块目录及源码

vi .gitmodules 删除项目目录下.gitmodules文件中子模块相关条目

vi .git/config 删除配置项中子模块相关条目

rm .git/module/* 删除模块下的子模块目录，每个子模块对应一个目录，注意只删除对应的子模块目录即可

git rm --cached 子模块名称（相对.git文件的<path>）