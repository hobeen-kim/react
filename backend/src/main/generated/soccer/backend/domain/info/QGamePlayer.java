package soccer.backend.domain.info;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGamePlayer is a Querydsl query type for GamePlayer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGamePlayer extends EntityPathBase<GamePlayer> {

    private static final long serialVersionUID = -37410968L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGamePlayer gamePlayer = new QGamePlayer("gamePlayer");

    public final soccer.backend.domain.game.QGame game;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final soccer.backend.domain.player.QPlayer player;

    public QGamePlayer(String variable) {
        this(GamePlayer.class, forVariable(variable), INITS);
    }

    public QGamePlayer(Path<? extends GamePlayer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGamePlayer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGamePlayer(PathMetadata metadata, PathInits inits) {
        this(GamePlayer.class, metadata, inits);
    }

    public QGamePlayer(Class<? extends GamePlayer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.game = inits.isInitialized("game") ? new soccer.backend.domain.game.QGame(forProperty("game"), inits.get("game")) : null;
        this.player = inits.isInitialized("player") ? new soccer.backend.domain.player.QPlayer(forProperty("player"), inits.get("player")) : null;
    }

}

