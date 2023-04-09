package soccer.backend.domain.player;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlayer is a Querydsl query type for Player
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlayer extends EntityPathBase<Player> {

    private static final long serialVersionUID = -345900925L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlayer player = new QPlayer("player");

    public final ListPath<soccer.backend.domain.game.Game, soccer.backend.domain.game.QGame> games = this.<soccer.backend.domain.game.Game, soccer.backend.domain.game.QGame>createList("games", soccer.backend.domain.game.Game.class, soccer.backend.domain.game.QGame.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final soccer.backend.domain.member.QMember member;

    public final StringPath name = createString("name");

    public final EnumPath<Position> position = createEnum("position", Position.class);

    public final ListPath<soccer.backend.domain.info.Record, soccer.backend.domain.info.QRecord> records = this.<soccer.backend.domain.info.Record, soccer.backend.domain.info.QRecord>createList("records", soccer.backend.domain.info.Record.class, soccer.backend.domain.info.QRecord.class, PathInits.DIRECT2);

    public QPlayer(String variable) {
        this(Player.class, forVariable(variable), INITS);
    }

    public QPlayer(Path<? extends Player> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlayer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlayer(PathMetadata metadata, PathInits inits) {
        this(Player.class, metadata, inits);
    }

    public QPlayer(Class<? extends Player> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new soccer.backend.domain.member.QMember(forProperty("member")) : null;
    }

}

