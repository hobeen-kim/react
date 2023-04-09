package soccer.backend.domain.info;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecord is a Querydsl query type for Record
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecord extends EntityPathBase<Record> {

    private static final long serialVersionUID = -1991163706L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecord record = new QRecord("record");

    public final NumberPath<Integer> assist = createNumber("assist", Integer.class);

    public final NumberPath<Integer> chanceMaking = createNumber("chanceMaking", Integer.class);

    public final NumberPath<Integer> contention = createNumber("contention", Integer.class);

    public final NumberPath<Integer> crossPass = createNumber("crossPass", Integer.class);

    public final NumberPath<Integer> dribble = createNumber("dribble", Integer.class);

    public final soccer.backend.domain.game.QGame game;

    public final EnumPath<soccer.backend.domain.player.Position> gamePosition = createEnum("gamePosition", soccer.backend.domain.player.Position.class);

    public final NumberPath<Integer> goal = createNumber("goal", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> intercept = createNumber("intercept", Integer.class);

    public final NumberPath<Integer> longPass = createNumber("longPass", Integer.class);

    public final EnumPath<Main> main = createEnum("main", Main.class);

    public final NumberPath<Integer> pass = createNumber("pass", Integer.class);

    public final soccer.backend.domain.player.QPlayer player;

    public final NumberPath<Integer> shoot = createNumber("shoot", Integer.class);

    public final NumberPath<Integer> successContention = createNumber("successContention", Integer.class);

    public final NumberPath<Integer> successCrossPass = createNumber("successCrossPass", Integer.class);

    public final NumberPath<Integer> successDribble = createNumber("successDribble", Integer.class);

    public final NumberPath<Integer> successLongPass = createNumber("successLongPass", Integer.class);

    public final NumberPath<Integer> successPass = createNumber("successPass", Integer.class);

    public final NumberPath<Integer> tackle = createNumber("tackle", Integer.class);

    public final NumberPath<Integer> timeIn = createNumber("timeIn", Integer.class);

    public final NumberPath<Integer> timeOut = createNumber("timeOut", Integer.class);

    public final NumberPath<Integer> touch = createNumber("touch", Integer.class);

    public final NumberPath<Integer> turnover = createNumber("turnover", Integer.class);

    public final NumberPath<Integer> validShoot = createNumber("validShoot", Integer.class);

    public QRecord(String variable) {
        this(Record.class, forVariable(variable), INITS);
    }

    public QRecord(Path<? extends Record> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecord(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecord(PathMetadata metadata, PathInits inits) {
        this(Record.class, metadata, inits);
    }

    public QRecord(Class<? extends Record> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.game = inits.isInitialized("game") ? new soccer.backend.domain.game.QGame(forProperty("game"), inits.get("game")) : null;
        this.player = inits.isInitialized("player") ? new soccer.backend.domain.player.QPlayer(forProperty("player"), inits.get("player")) : null;
    }

}

