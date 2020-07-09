package dnt.entity;

import dnt.entity.Audit.AuditDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


/**
 * The persistent class for the feedback database table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "feedback")
@NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f where delete_flag = 'N'")
public class Feedback extends AuditDate {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id", unique = true, nullable = false)
    private int feedbackId;

    @Column(nullable = false, length = 300)
    private String content;

    @Column(nullable = false)
    private int star;

    //bi-directional many-to-one association to Guest
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_phone", nullable = false)
    private Guest guest;

}