package cn.axboy.demo.mixjpajooq.conf;

import org.jooq.*;
import org.jooq.impl.DefaultExecuteListener;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.jooq.impl.DefaultRecordListener;
import org.jooq.impl.DefaultRecordListenerProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/12/7 下午6:04
 * jooq配置
 */
@Configuration
public class JooqConfig {

    @Bean
    public ExecuteListenerProvider executeListenerProvider() {
        return new DefaultExecuteListenerProvider(new DefaultExecuteListener() {
            //TODO
        });
    }

    @Bean
    public RecordListenerProvider recordListenerProvider() {
        return new DefaultRecordListenerProvider(new DefaultRecordListener() {
            @Override
            public void insertStart(RecordContext ctx) {
                String username = "test-jooq";

                Record record = ctx.record();
                long time = System.currentTimeMillis();

                //created_by
                Field<String> created_by = (Field<String>) record.field("created_by");
                Optional.ofNullable(created_by).ifPresent(stringField -> record.setValue(created_by, username));

                //create_time
                Field<Long> created_time = (Field<Long>) record.field("created_time");
                Optional.ofNullable(created_time).ifPresent(longField -> record.setValue(created_time, time));

                //last_modified_time
                Field<Long> last_modified_time = (Field<Long>) record.field("last_modified_time");
                Optional.ofNullable(last_modified_time).ifPresent(field -> record.setValue(last_modified_time, time));

                //last_modified_by
                Field<String> last_modified_by = (Field<String>) record.field("last_modified_by");
                Optional.ofNullable(last_modified_by).ifPresent(stringField -> record.setValue(last_modified_by, username));

                //flag
                Field<Integer> flag = (Field<Integer>) record.field("flag");
                Optional.ofNullable(flag).ifPresent(field -> record.setValue(flag, 1));
            }

            @Override
            public void updateStart(RecordContext ctx) {
                Record record = ctx.record();
                long time = System.currentTimeMillis();
                String username = "test-jooq";
                //last_modified_time
                Field<Long> last_modified_time = (Field<Long>) record.field("last_modified_time");
                Optional.ofNullable(last_modified_time).ifPresent(field -> record.setValue(last_modified_time, time));

                //last_modified_by
                Field<String> last_modified_by = (Field<String>) record.field("last_modified_by");
                Optional.ofNullable(last_modified_by).ifPresent(stringField -> record.setValue(last_modified_by, username));
            }
        });
    }
}
