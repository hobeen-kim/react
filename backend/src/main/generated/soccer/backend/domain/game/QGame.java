package soccer.backend.domain.game;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGame is a Querydsl query type for Game
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGame extends EntityPathBase<Game> {

    private static final long serialVersionUID = -1046299421L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGame game = new QGame("game");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final ListPath<soccer.backend.domain.info.DotRecord, soccer.backend.domain.info.QDotRecord> dotRecords = this.<soccer.backend.domain.info.DotRecord, soccer.backend.domain.info.QDotRecord>createList("dotRecords", soccer.backend.domain.info.DotRecord.class, soccer.backend.domain.info.QDotRecord.class, PathInits.DIRECT2);

    public final NumberPath<Integer> GA = createNumber("GA", Integer.class);

    public final StringPath gameName = createString("gameName");

    public final NumberPath<Integer> GF = createNumber("GF", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath location = createString("location");

    public final soccer.backend.domain.member.QMember member;

    public final StringPath opponent = createString("opponent");

    public final ListPath<soccer.backend.domain.player.Player, soccer.backend.domain.player.QPlayer> players = this.<soccer.backend.domain.player.Player, soccer.backend.domain.player.QPlayer>createList("players", soccer.backend.domain.player.Player.class, soccer.backend.domain.player.QPlayer.class, PathInits.DIRECT2);

    public final ListPath<soccer.backend.domain.info.Record, soccer.backend.domain.info.QRecord> records = this.<soccer.backend.domain.info.Record, soccer.backend.domain.info.QRecord>createList("records", soccer.backend.domain.info.Record.class, soccer.backend.domain.info.QRecord.class, PathInits.DIRECT2);

    public QGame(String variable) {
        this(Game.class, forVariable(variable), INITS);
    }

    public QGame(Path<? extends Game> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGame(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGame(PathMetadata metadata, PathInits inits) {
        this(Game.class, metadata, inits);
    }

    public QGame(Class<? extends Game> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new soccer.backend.domain.member.QMember(forProperty("member")) : null;
    }

}

