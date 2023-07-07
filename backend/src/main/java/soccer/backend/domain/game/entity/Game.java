package soccer.backend.domain.game.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import soccer.backend.domain.dotrecord.entity.DotRecord;
import soccer.backend.domain.record.entity.Record;
import soccer.backend.auth.entity.Member;
import soccer.backend.domain.player.entity.Player;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String gameName;
    private String opponent;
    private String location;
    private Integer GF;
    private Integer GA;
    private LocalDate createdAt;

    //Member 와 다대일
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        member.addGame(this);
    }

    //dotRecord 와 일대다
    @OneToMany(mappedBy = "game", cascade = CascadeType.REMOVE)
    private List<DotRecord> dotRecords = new ArrayList<>();
    public void addDotRecord(DotRecord dotRecord) {
        dotRecords.add(dotRecord);
    }

    //Record 와 일대다
    @OneToMany(mappedBy = "game", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Record> records = new ArrayList<>();

    //player 와 다대다
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "game_player",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    @JsonBackReference
    private List<Player> players = new ArrayList<>();
    public void addPlayer(Player player) {
        players.add(player);
    }
    public void removePlayer(Player player) {
        players.remove(player);
    }
}
