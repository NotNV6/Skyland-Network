package rip.skyland.commons.redis;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;
import rip.skyland.commons.module.Module;
import rip.skyland.commons.redis.handler.RedisHandler;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


@Data
public class RedisAPI extends Module {

    private static RedisAPI instance;

    private final String hostname;
    private final int port;

    // authentication fields
    private final String password;

    // jedis fields
    private JedisPool jedisPool;

    private final List<RedisHandler> redisHandlers = new ArrayList<>();
    private final JsonParser jsonParser = new JsonParser();

    /**
     * Constructor for creating the module API.
     * If the password is empty, authentication will be disabled.
     *
     * @param hostname the ip address
     * @param password the password to authenticate with
     * @param port     the port of the database
     */
    public RedisAPI(String hostname, String password, int port) {
        instance = this;

        this.hostname = hostname;
        this.password = password;
        this.port = port;
    }

    @Override
    public void enable() {
        super.enable();

        jedisPool = new JedisPool(hostname, port);
    }

    /**
     * Write a message to the Redis database.
     *
     * @param channel the channel to write the message in
     * @param object  the message
     */
    public void write(String channel, JsonObject object) {
        this.runCommand(jedis -> jedis.publish(channel, object.toString()));
    }

    public void registerHandler(RedisHandler redisHandler) {
        this.runCommand(jedis -> jedis.subscribe(redisHandler, redisHandler.getChannels()));
    }

    /**
     * Sends a packet through redis
     *
     * @param consumer the callback to be executed
     */
    private void runCommand(Consumer<Jedis> consumer) {
        new Thread(() -> {
            Jedis jedis = jedisPool.getResource();

            if (jedis != null) {
                if (!password.equals("")) {
                    jedis.auth(password);
                }

                consumer.accept(jedis);
                jedisPool.returnResource(jedis);
            }
        }).start();
    }

    public static RedisAPI get() {
        return instance;
    }
}