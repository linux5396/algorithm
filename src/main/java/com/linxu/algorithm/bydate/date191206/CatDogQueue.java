package com.linxu.algorithm.bydate.date191206;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author linxu
 * @date 2019/12/5
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class CatDogQueue {
    private Queue<PetQueueNode> catQueue = new LinkedList<>();
    private Queue<PetQueueNode> dogQueue = new LinkedList<>();
    private long timestamp = 0;

    public void add(Pet pet) {
        if (pet != null) {
            if (pet.getType().equals("dog")) {
                this.dogQueue.offer(new PetQueueNode(pet, timestamp++));
            } else if (pet.getType().equals("cat")) {
                this.catQueue.offer(new PetQueueNode(pet, timestamp++));
            } else {
                throw new IllegalArgumentException("error,this is not cat or dog!");
            }
        }
    }

    public Pet pollAll() {
        if (!dogQueue.isEmpty() && !catQueue.isEmpty()) {
            if (this.catQueue.peek().getTimestamp() < this.dogQueue.peek().getTimestamp()) {
                return catQueue.poll().getPet();
            } else {
                return dogQueue.poll().getPet();
            }
        } else if (!dogQueue.isEmpty()) {
            return dogQueue.poll().getPet();
        } else {
            return catQueue.poll().getPet();
        }
    }

    public Pet pollDog() {
        if (!this.dogQueue.isEmpty()) {
            return dogQueue.poll().getPet();
        } else {
            return null;
        }
    }

    public Pet pollCat() {
        if (!this.catQueue.isEmpty()) {
            return catQueue.poll().getPet();
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return dogQueue.isEmpty() && catQueue.isEmpty();
    }

    public boolean isEmptyCat() {
        return catQueue.isEmpty();
    }

    public boolean isEmptyDog() {
        return dogQueue.isEmpty();
    }

    static class PetQueueNode {
        private Pet pet;
        private long timestamp;

        public PetQueueNode(Pet pet, long timestamp) {
            this.pet = pet;
            this.timestamp = timestamp;
        }

        public Pet getPet() {
            return pet;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
}
