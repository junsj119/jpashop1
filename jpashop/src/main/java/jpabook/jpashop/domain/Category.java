package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id
    @GeneratedValue
    @Column(name="catrgory_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name="category_item",                            //중간 테이블 매핑
            joinColumns = @JoinColumn(name="category_id"),      //중간 테이블에 있는 내 아이디
            inverseJoinColumns = @JoinColumn(name="item_id"))   //중간 테이블에 있는 상대방 아이디
    private List<Item>items = new ArrayList<>();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category>child = new ArrayList<>();

    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }
}
