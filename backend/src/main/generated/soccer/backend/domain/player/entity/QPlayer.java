package soccer.backend.domain.player.entity;

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

    private static final long serialVersionUID = 14871466L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlayer player = new QPlayer("player");

    public final ListPath<soccer.backend.domain.game.entity.Game, soccer.backend.domain.game.entity.QGame> games = this.<soccer.backend.domain.game.entity.Game, soccer.backend.domain.game.entity.QGame>createList("games", soccer.backend.domain.game.entity.Game.class, soccer.backend.domain.game.entity.QGame.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final soccer.backend.auth.entity.QMember member;

    public final StringPath name = createString("name");

    public final EnumPath<Position> position = createEnum("position", Position.class);

    public final ListPath<soccer.backend.domain.record.entity.Record, soccer.backend.domain.record.entity.QRecord> records = this.<soccer.backend.domain.record.entity.Record, soccer.backend.domain.record.entity.QRecord>createList("records", soccer.backend.domain.record.entity.Record.class, soccer.backend.domain.record.entity.QRecord.class, PathInits.DIRECT2);

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
        this.member = inits.isInitialized("member") ? new soccer.backend.auth.entity.QMember(forProperty("member")) : null;
    }

}

