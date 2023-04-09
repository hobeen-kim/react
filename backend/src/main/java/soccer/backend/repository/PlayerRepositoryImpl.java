package soccer.backend.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import soccer.backend.domain.player.QPlayer;

@RequiredArgsConstructor
public class PlayerRepositoryImpl implements PlayerRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public void deleteByIdQdsl(Long id) {
       queryFactory.delete(QPlayer.player)
               .where(QPlayer.player.id.eq(id))
               .execute();
    }
}
