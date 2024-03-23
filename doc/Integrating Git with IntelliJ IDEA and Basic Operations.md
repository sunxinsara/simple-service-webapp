# Integrating Git with IntelliJ IDEA and Basic Operations

IntelliJ IDEA is a powerful IDE that supports integration with Git, allowing developers to manage version control for their projects directly within the IDE. This guide covers the basic steps to integrate Git with IntelliJ IDEA and how to perform essential Git operations such as committing changes and updating projects.

## Integrating Git with IntelliJ IDEA

1. **Ensure Git is Installed**: Before integrating Git with IDEA, make sure Git is installed on your computer. You can download it from [git-scm.com](https://git-scm.com/).

2. **Configure Git in IDEA**: Open IntelliJ IDEA and navigate to `File` -> `Settings` (on Windows and Linux) or `IntelliJ IDEA` -> `Preferences` (on macOS). Go to `Version Control` -> `Git`. Click the `...` button next to the `Path to Git executable` and select the path to the Git executable on your computer, usually `C:\Program Files\Git\bin\git.exe` on Windows or `/usr/bin/git` on Linux and macOS.
   
   <img title="" src="./images/2024-03-23-00-20-10-image.png" alt="" data-align="center">
   
   ![](./images/2024-03-23-00-21-00-image.png)

## Create a Git repository

The traditional way to initiate a new git repository is to use command ‘git init’. Another way to initiate. Using GitHub create a repository, then clone to your local machine.

<img title="" src="./images/2024-03-23-11-29-18-image.png" alt="" data-align="center">

## Committing Changes with Git in IntelliJ IDEA

As for Making commits, you can use git command with git bash. Or using your IDE, nowadays, most IDE have already integrated git. I am using IDEA IntelliJ, with this tool you can see the picture as below. 

1. **Make Changes**: Make changes to your project files.

2. **Open the Commit Window**:
   
   - Navigate to `VCS > Commit` (or use the shortcut `Ctrl+K` on Windows/Linux, `Cmd+K` on macOS).

3. **Select Files to Commit**: In the Commit window, select the files you want to commit. You can also enter a commit message describing the changes.
   
   <img title="" src="./images/2024-03-23-11-34-33-image.png" alt="" data-align="center">

4. **Commit Changes**:
   
   - Click the `Commit` button (or `Commit and Push` if you want to immediately push the changes to a remote repository).
     
     <img title="" src="./images/2024-03-23-11-33-16-image.png" alt="" data-align="center">

If you want to ingore this warning, you can choose commit anyway.

<img title="" src="./images/2024-03-23-11-36-24-image.png" alt="" data-align="center">

When you successfully commit, you can see your commit in log pan.

<img title="" src="./images/2024-03-23-11-38-09-image.png" alt="" data-align="center">

## Create a branch

In the IDE, the visualized git tool pan, you can easily find the plus symbol to create a new branch.

<img title="" src="./images/2024-03-23-11-40-15-image.png" alt="" data-align="center">

<img title="" src="./images/2024-03-23-11-40-36-image.png" alt="" data-align="center">

## Merge branches

Add a commit in test branch

<img title="" src="./images/2024-03-23-11-43-09-image.png" alt="" data-align="center">

Go back to master branch by clicking the branch choice.
Go to the top menu bar. Find Git -> Merge.

<img title="" src="./images/2024-03-23-11-43-47-image.png" alt="" data-align="center">

<img title="" src="./images/2024-03-23-11-44-05-image.png" alt="" data-align="center"><img title="" src="./images/2024-03-23-11-44-25-image.png" alt="" data-align="center">

## Handle merge conflicts

Conflicts in Git occur when multiple changes are made to the same part of a file in different branches or commits and Git is unable to automatically merge them. This usually happens when two developers are working on the same codebase and make different changes to the same lines of code or when a developer's local branch diverges significantly from the branch they are trying to merge into or rebase onto.

### Simulate a local merge conflict.

<img title="" src="./images/2024-03-23-11-53-00-image.png" alt="" data-align="center">

<img title="" src="./images/2024-03-23-11-53-32-image.png" alt="" data-align="center">

Click the alpply button and confim your changes.

<img title="" src="./images/2024-03-23-11-54-13-image.png" alt="" data-align="center">

After merge check dev branch log.

<img title="" src="./images/2024-03-23-11-55-03-image.png" alt="" data-align="center">

### Simulate a pull request merge conflict.

Simulate a conflict in branch test and testConflict. Edit same line, the password line.

![2024-03-23-12-25-23-image.png](C:\Users\sunxin\AppData\Roaming\marktext\images\2024-03-23-12-25-23-image.png)

![2024-03-23-12-25-57-image.png](C:\Users\sunxin\AppData\Roaming\marktext\images\2024-03-23-12-25-57-image.png)

![2024-03-23-12-26-29-image.png](C:\Users\sunxin\AppData\Roaming\marktext\images\2024-03-23-12-26-29-image.png)

Then push all these changes to GitHub.

![2024-03-23-12-27-01-image.png](C:\Users\sunxin\AppData\Roaming\marktext\images\2024-03-23-12-27-01-image.png)

Create a pull request to merge test branch into testconflict branch. You will see the warnning that GitHub can not automaically merge because of the conflict.

![2024-03-23-12-27-58-image.png](C:\Users\sunxin\AppData\Roaming\marktext\images\2024-03-23-12-27-58-image.png)

![2024-03-23-12-28-53-image.png](C:\Users\sunxin\AppData\Roaming\marktext\images\2024-03-23-12-28-53-image.png)

You need to resolve the conflict manully. Click the button "Resolve conflicts".

![2024-03-23-12-30-16-image.png](C:\Users\sunxin\AppData\Roaming\marktext\images\2024-03-23-12-30-16-image.png)

You will enter to the page showing your difference in those two branches.

![2024-03-23-12-30-52-image.png](C:\Users\sunxin\AppData\Roaming\marktext\images\2024-03-23-12-30-52-image.png)

Edit the code then click the button "Mark as resolved"

![2024-03-23-12-31-37-image.png](C:\Users\sunxin\AppData\Roaming\marktext\images\2024-03-23-12-31-37-image.png)

Then commit merge.

![2024-03-23-12-31-48-image.png](C:\Users\sunxin\AppData\Roaming\marktext\images\2024-03-23-12-31-48-image.png)

Congras, here you have already reolved the conflicted. 

![2024-03-23-12-32-41-image.png](C:\Users\sunxin\AppData\Roaming\marktext\images\2024-03-23-12-32-41-image.png)

## Updating Project with Git in IntelliJ IDEA

1. **Fetch Changes**:
   
   - To fetch the latest changes from the remote repository, go to `VCS > Git > Fetch`.
     
     <img title="" src="./images/2024-03-23-00-14-00-image.png" alt="" data-align="center">

2. **Pull Changes**:
   
   - To merge the latest changes into your current branch, navigate to `VCS > Git > Pull`.
   - In the dialog that appears, select the remote branch you want to pull changes from and click `Pull`.

Integrating Git with IntelliJ IDEA streamlines version control, making it easier to manage project changes, commit new code, and keep up with the latest updates from your team. This integration enhances productivity and ensures a smoother development process.
