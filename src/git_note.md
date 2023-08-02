# git 

## 基本指令

`git init`  创建一个空仓库



`git add 文件名` 工作区->暂存区

`git commit 文件名` 暂存区->本地仓库

>`git commit -m "注释"` 可在提交时添加注释

>修改已有的文件或创建新的文件都会先保存在工作区
>
>每次提交到仓库都会有提交记录



`git status` 查看当前仓库的状态

`git log [option]`  查看提交日志

+ option
  + --all 显示所有分支
  + --pretty=oneline 显示为一行
  + --abbrev-commit 使得输出的id更简洁
  + --graph 以图的形式输出

`git reset --hard commitID` 回退到某个版本

`git reflog` 查看所有历史提交（包括回退版本的动作）



`touch .ignore` 对.ignore 内的文件或文件类型不予以git管理（即不提交这些文件给git）





## 分支

### 基本指令

`git branch ` 查看所有分支

`git branch 分支名` 新建一个分支

`git checkout 分支名` 切换到另一个分支

`git checkout -b 分支名` 新建并切换到这个分支



`git branch -d` 删除分支

> 此指令不可删除还未merge到主分支的分支

`git branch -D`  强制删除分支



`git merge 分支名` 将该分支合并到目前工作分支

> 一般将其他分支合并到master分支上，故合并前要先checkout master

> <u>合并时有冲突怎么办：==手动打开文件，找到冲突处手动修改，再提交上传即可==</u>



### 分支规范

一般有如下分支使用规范

+ master （发布分支）
+ develop （开发分支）
+ feature/xxxx （功能开发分支）
+ hotfit/bug_xxxx  （bug修复分支）
+ 其他，如test,pre