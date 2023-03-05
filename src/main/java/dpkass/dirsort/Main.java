package dpkass.dirsort;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dpkass.dirsort.datastructure.Configuration;
import java.io.File;
import java.io.IOException;

public class Main {

  public static void main(String[] args) {
    System.out.println(new File("configuration.yaml").getAbsolutePath());
    Configuration configuration = getConfig();
    if (configuration == null)
      return;

    File source, target;
    try {
      source = new File(configuration.source());
      target = new File(configuration.target());
    } catch (NullPointerException e) {
      System.err.println("Source & target directories must be specified.");
      return;
    }

    Sorter sorter = new Sorter(
        source,
        target,
        configuration.createdFrom(),
        configuration.createdTo(),
        configuration.lastModifiedFrom(),
        configuration.lastModifiedTo(),
        configuration.copyByCreated(),
        configuration.copyByLastModified(),
        configuration.cleanup()
    );
    sorter.run();
  }

  private static Configuration getConfig() {
    try {
      ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
      mapper.registerModule(new JavaTimeModule());
      return mapper.readValue(new File("configuration.yaml"), Configuration.class);
    } catch (IOException e) {
      System.err.println("Couldn't parse configuration.yaml.");
      return null;
    }
  }
}
