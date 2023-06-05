# Blog
Blog via Java Spring

Local setup
1. Create folder for this project
2. Run `git init` command to initialize git
3. Check your remote connected repos via `git remote` or `git remote -v` (in last case, it will also provide list of the URL links for each connection)
4. Setup remote connection using `git remote add <name> <url>`
5. `git clone`, `git fetch` and `git pull` can be used to read from a remote repository. We recommend to use `git clone <remote>` for first code pull but after that, please use `git fetch` as it's safer
1.1 One can also simply download code repo but don't forget to run steps 2-4 after this.

Start working
We're using Release Branching Strategy approach here, so please keep in mind that you should be committing to the release branch first and after release developer branch - main will be updated for further usage. Steps:
1. Checkout to the feature branch:
`git checkout -b <feature-branch-name>`
2. Provide updates and commit them to the feature branch:
`git status` - checks for the update
`git add <file-name>` / `--all` - takes a modified file in your working directory and places the modified version in a staging area.
`git commit <commit-name>` - takes everything from the staging area and makes a permanent snapshot of the current state of your repository that is associated with a unique identifier.
3. Pushing updates to the remote repo:
`git push -u <remote repo> <feature-branch>` - using SSH-key or credentials you'll be pushing your updates to the remote repo
4. Check your updates in Github and create pull request
5. After updates are approved pull request will be merged into the release branch and feature branch will be deleted.

Updating local repo
1. Remove your local feature branch
`git branch –delete <feature-branch>` or
`git branch -d <feature-branch>`
2. Pull most recent code version from the remote repo:
`git fetch <remote-repo>/main` 
3. `git checkout origin/main` to make sure all updates are there
4. Swith back to the main branch. In case there are changes in your local branch, please commit them via `git add`, `git commit -m "comment"` commands. After that run
`git rebase origin/main` - this will get the updates and place your changes on top of it.

- Spring Data JPA
- Postgres Database
- Thymeleaf
- Spring Security
- Model View Controller (MVC) architecture

LICENSE
MIT License

Copyright (c) 2022 Wazoo Enterprises Inc.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.