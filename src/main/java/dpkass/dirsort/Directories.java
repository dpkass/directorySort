package dpkass.dirsort;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Directories {

  Map<Integer, Map<Integer, File>> directories = new HashMap<>();

  void add(int year, int month, File dir) {
    Map<Integer, File> months = directories.getOrDefault(year, new HashMap<>(12));
    months.put(month, dir);
    directories.putIfAbsent(year, months);
  }

  File get(int year, int month) {
    return directories.getOrDefault(year, new HashMap<>()).getOrDefault(month, null);
  }
}
