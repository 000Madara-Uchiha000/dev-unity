package uz.pdp.devunity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.devunity.entity.base.BaseUserEntity;

@Getter
@Setter
@Entity
public class PrizeWinner extends BaseUserEntity {
    @OneToOne
    @JoinColumn(name = "event_prize_id")
    private EventPrize eventPrize;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
