package soccer.backend.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -38873442L;

    public static final QMember member = new QMember("member1");

    public final EnumPath<Authority> authority = createEnum("authority", Authority.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final ListPath<soccer.backend.domain.game.entity.Game, soccer.backend.domain.game.entity.QGame> games = this.<soccer.backend.domain.game.entity.Game, soccer.backend.domain.game.entity.QGame>createList("games", soccer.backend.domain.game.entity.Game.class, soccer.backend.domain.game.entity.QGame.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memberId = createString("memberId");

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final ListPath<soccer.backend.domain.player.entity.Player, soccer.backend.domain.player.entity.QPlayer> players = this.<soccer.backend.domain.player.entity.Player, soccer.backend.domain.player.entity.QPlayer>createList("players", soccer.backend.domain.player.entity.Player.class, soccer.backend.domain.player.entity.QPlayer.class, PathInits.DIRECT2);

    public final ListPath<soccer.backend.domain.post.entity.Post, soccer.backend.domain.post.entity.QPost> posts = this.<soccer.backend.domain.post.entity.Post, soccer.backend.domain.post.entity.QPost>createList("posts", soccer.backend.domain.post.entity.Post.class, soccer.backend.domain.post.entity.QPost.class, PathInits.DIRECT2);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

