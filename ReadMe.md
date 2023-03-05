# Directory Sorter

Basic program that copies all files, that match given parameters, from one directories into another. The files are categorized by year and
month in the target directories. May copy multiple times, if copies are triggered in different categories.

## Run

Put properties in a configuration.yaml file.  
You will need a [JRE](#Installing-JRE).

<pre>java -jar directorySort.jar</pre>

## Installing JRE

**Option 1**  
Follow instruction on Orcale Website. Only JRE is needed!

+ [macOS](https://docs.oracle.com/javase/9/install/installation-jdk-and-jre-macos.htm#JSJIG-GUID-0071963E-D247-4D15-BF49-AD19C7260740)
+ [Linux](https://docs.oracle.com/javase/9/install/installation-jdk-and-jre-linux-platforms.htm#JSJIG-GUID-737A84E4-2EFF-4D38-8E60-3E29D1B884B8)
+ [Windows](https://docs.oracle.com/javase/9/install/installation-jdk-and-jre-microsoft-windows-platforms.htm#JSJIG-GUID-A7E27B90-A28D-4237-9383-A58B416071CA)

**Option 2 (macOS only)**  
If homebrew is installed ([no](#installing-homebrew)), just run

```bash
brew install java
```

Then run

```bash
java --version
```

If the result starts with ``The operation couldn’t be completed.``, run

```bash
sudo ln -sfn /opt/homebrew/opt/openjdk/libexec/openjdk.jdk \ /Library/Java/JavaVirtualMachines/openjdk.jdk
```

### Installing Homebrew

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

## .yaml syntax

```yaml
foo:
  bar:
    foobar: ...
```

## Properties

### Required

```yaml
directory:
  source: "src"
  target: "tgt"
```

### Optional

```yaml
options:
  date:
    any:
      from: YYYY-MM-DD
      to: YYYY-MM-DD
    last_modified:
      from: YYYY-MM-DD
      to: YYYY-MM-DD
    created:
      from: YYYY-MM-DD
      to: YYYY-MM-DD
  copy:
    created: bool
    last_modified: bool
  cleanup: bool
```

If ``date.any`` is used, the others are ignored.  
If ``cleanup`` is disabled empty directories are kept.

#### Defaults
```yaml
options:
  date:
    any:
      from: 1970-01-01 # EPOCH
      to: now
  copy:
    created: true
    last_modified: true
  cleanup: true
```