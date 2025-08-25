package com.template.springboot339jdk17template.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    @Value("${spring.data.redis.host}")
    private String redisHost;


    @Value("${spring.data.redis.port}")
    private String redisPort;


    @Value("${spring.data.redis.password}")
    private String redisPwd;
    /** Redis单机模式 */
    @Bean
    public RedissonClient singletonModeRedisson() {
        // 创建Config实例(配置类)
        Config config = new Config();
        // 设置单机模式下的服务器地址
        config.useSingleServer()
                // 设置Redis服务器地址
                .setAddress("redis://"+redisHost+":"+redisPort)
                // 设置Redis服务器密码
                .setPassword(redisPwd)
                // 设置Redis数据库索引，默认为0
                .setDatabase(0);
        // 创建RedissonClient实例
        return Redisson.create(config);
    }

    /** Redis主从模式 */
    /*
    @Bean
    public RedissonClient masterSlaveModeRedisson() {
        Config config = new Config();
        // 设置主从模式下的服务器地址
        config.useMasterSlaveServers()
         		// 主服务器IP
                .setMasterAddress("redis://192.168.230.131:6379")
                // 从服务器IP
                .addSlaveAddress("redis://192.168.230.132:6379", "redis://192.168.230.133:6379")
                .setPassword("123456");
        return Redisson.create(config);
    }
    */

    /** Redis哨兵模式 */
    /*
    @Bean
    public RedissonClient sentinelModeRedisson() {
        Config config = new Config();
        // 设置哨兵模式下的服务器地址
        config.useSentinelServers()
                .setMasterName("myMaster")
                .addSentinelAddress(
                        "redis://192.168.230.131:6379",
                        "redis://192.168.230.132:6379",
                        "redis://192.168.230.133:6379")
                .setPassword("123456");
        return Redisson.create(config);
    }
    */

    /** Redis集群模式 */
    /*
    @Bean
    public RedissonClient clusterModeRedisson() {
        Config config = new Config();
        // 设置集群模式下的服务器地址
        config.useClusterServers()
                .addNodeAddress(
                        "redis://192.168.230.131:6379",
                        "redis://192.168.230.132:6379",
                        "redis://192.168.230.133:6379")
                .setPassword("123456")
                // 设置集群状态扫描间隔时间（单位为毫秒）
                .setScanInterval(2000);
        return Redisson.create(config);
    }
    */
}
