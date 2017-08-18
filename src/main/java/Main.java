import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.DatabaseId;
import com.google.cloud.spanner.Key;
import com.google.cloud.spanner.Spanner;
import com.google.cloud.spanner.SpannerOptions;
import com.google.cloud.spanner.Struct;
import com.google.common.collect.ImmutableList;

public class Main {
  private static final String INSTANCE = "foo";
  private static final String DATABASE = "bar";

  public static void main(final String... args) {
    final SpannerOptions options = SpannerOptions
        .newBuilder()
        .build();
    final Spanner service = options.getService();
    final DatabaseClient databaseClient = service.getDatabaseClient(DatabaseId.of(options.getProjectId(), INSTANCE, DATABASE));
    final Struct struct = databaseClient.singleUse().readRow("foo", Key.of("bar"), ImmutableList.<String>of());
    System.out.println(struct);
  }
}
