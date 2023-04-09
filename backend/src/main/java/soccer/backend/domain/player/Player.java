package soccer.backend.domain.player;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import soccer.backend.domain.game.Game;
import soccer.backend.domain.info.DotRecord;
import soccer.backend.domain.info.Record;
import soccer.backend.domain.member.Member;
import java.util.ArrayList;
import java.util.List;



@RequiredArgsConstructor
@Entity
@Getter
@Setter
public class Player {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Position position;

    //CreatePlayerRequestDto 에서 Player 객체 생성
    public Player(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    //Member 와 다대일
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;
    public void setMember(Member member) {
        this.member = member;
        member.addPlayer(this);
    }

    //Record 와 일대다
    @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Record> records = new ArrayList<>();

    //game 과 다대다
    @ManyToMany(mappedBy = "players", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    private List<Game> games = new ArrayList<>();
    public void addGame(Game game) {
        games.add(game);
    }
    public void removeGame(Game game) {
        games.remove(game);
    }
}
