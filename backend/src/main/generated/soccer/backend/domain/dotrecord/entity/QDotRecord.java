package soccer.backend.domain.dotrecord.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDotRecord is a Querydsl query type for DotRecord
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDotRecord extends EntityPathBase<DotRecord> {

    private static final long serialVersionUID = -202316946L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDotRecord dotRecord = new QDotRecord("dotRecord");

    public final soccer.backend.domain.game.entity.QGame game;

    public final EnumPath<soccer.backend.domain.player.entity.Position> gamePosition = createEnum("gamePosition", soccer.backend.domain.player.entity.Position.class);

    public final NumberPath<Integer> gameTime = createNumber("gameTime", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> playerId = createNumber("playerId", Long.class);

    public final StringPath playerName = createString("playerName");

    public final BooleanPath shoot = createBoolean("shoot");

    public final NumberPath<Float> shootX = createNumber("shootX", Float.class);

    public final NumberPath<Float> shootY = createNumber("shootY", Float.class);

    public final BooleanPath validShoot = createBoolean("validShoot");

    public final NumberPath<Float> x = createNumber("x", Float.class);

    public final NumberPath<Float> y = createNumber("y", Float.class);

    public QDotRecord(String variable) {
        this(DotRecord.class, forVariable(variable), INITS);
    }

    public QDotRecord(Path<? extends DotRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDotRecord(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDotRecord(PathMetadata metadata, PathInits inits) {
        this(DotRecord.class, metadata, inits);
    }

    public QDotRecord(Class<? extends DotRecord> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.game = inits.isInitialized("game") ? new soccer.backend.domain.game.entity.QGame(forProperty("game"), inits.get("game")) : null;
    }

}

