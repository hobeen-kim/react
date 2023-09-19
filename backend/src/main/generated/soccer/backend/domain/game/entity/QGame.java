package soccer.backend.domain.game.entity;

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

    private static final long serialVersionUID = 1327444300L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGame game = new QGame("game");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final ListPath<soccer.backend.domain.dotrecord.entity.DotRecord, soccer.backend.domain.dotrecord.entity.QDotRecord> dotRecords = this.<soccer.backend.domain.dotrecord.entity.DotRecord, soccer.backend.domain.dotrecord.entity.QDotRecord>createList("dotRecords", soccer.backend.domain.dotrecord.entity.DotRecord.class, soccer.backend.domain.dotrecord.entity.QDotRecord.class, PathInits.DIRECT2);

    public final NumberPath<Integer> GA = createNumber("GA", Integer.class);

    public final StringPath gameName = createString("gameName");

    public final NumberPath<Integer> GF = createNumber("GF", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath location = createString("location");

    public final soccer.backend.auth.entity.QMember member;

    public final StringPath opponent = createString("opponent");

    public final ListPath<soccer.backend.domain.player.entity.Player, soccer.backend.domain.player.entity.QPlayer> players = this.<soccer.backend.domain.player.entity.Player, soccer.backend.domain.player.entity.QPlayer>createList("players", soccer.backend.domain.player.entity.Player.class, soccer.backend.domain.player.entity.QPlayer.class, PathInits.DIRECT2);

    public final ListPath<soccer.backend.domain.record.entity.Record, soccer.backend.domain.record.entity.QRecord> records = this.<soccer.backend.domain.record.entity.Record, soccer.backend.domain.record.entity.QRecord>createList("records", soccer.backend.domain.record.entity.Record.class, soccer.backend.domain.record.entity.QRecord.class, PathInits.DIRECT2);

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
        this.member = inits.isInitialized("member") ? new soccer.backend.auth.entity.QMember(forProperty("member")) : null;
    }

}

