package soccer.backend.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1707492221L;

    public static final QUser user = new QUser("user");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final ListPath<soccer.backend.domain.game.Game, soccer.backend.domain.game.QGame> games = this.<soccer.backend.domain.game.Game, soccer.backend.domain.game.QGame>createList("games", soccer.backend.domain.game.Game.class, soccer.backend.domain.game.QGame.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final ListPath<soccer.backend.domain.player.Player, soccer.backend.domain.player.QPlayer> players = this.<soccer.backend.domain.player.Player, soccer.backend.domain.player.QPlayer>createList("players", soccer.backend.domain.player.Player.class, soccer.backend.domain.player.QPlayer.class, PathInits.DIRECT2);

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final StringPath userName = createString("userName");

    public QUser(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QUser(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

