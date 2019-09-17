package com.linxu.algorithm.bydate.date190909;

import sun.management.resources.agent;
import sun.tools.jar.resources.jar;
import sun.util.logging.resources.logging;

import java.util.*;

/**
 * @author linxu
 * @date 2019/9/9
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 找出数组中重复的元素，普遍情况下
 */
public class CheckRepeatedElements {
    /**
     * 使用排序遍历，总体的复杂度在 n(logn+1)
     *
     * @param array a
     * @return t or f
     */
    public static boolean hasRepeated(int[] array) {
        //执行排序 nlogn
        Arrays.sort(array);
        int i = 0;
        //最坏是n
        while (i < array.length - 1) {
            if (array[i] == array[i + 1]) {
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * @param array
     * @return
     * @throws NullPointerException
     */
    private static int[] checkRepeated(int[] array) throws NullPointerException {
        if (array == null || array.length == 0) {
            throw new NullPointerException("array is empty");
        }
        Arrays.sort(array);
        //最大重复长度
        Set<Integer> set = new TreeSet<>();
        int i = 0;
        //最坏是n
        while (i < array.length - 1) {
            if (array[i] == array[i + 1]) {
                set.add(array[i]);
            }
            i++;
        }
        int[] res = new int[set.size()];
        Iterator iterator = set.iterator();
        int j = 0;
        while (iterator.hasNext()) {
            res[j++] = (Integer) iterator.next();
        }
        return res;
    }

    /**
     * 如果采用哈希算法，实际的空间复杂度为N，时间复杂度也为N。（取极限）
     * 不适用哈希算法，即采用先排序，后遍历，时间复杂度为NlogN+N=N(NlogN+1)
     * 相比较而下，采用哈希算法，时间复杂度为采用排序算法的1/(NlogN+1)
     * 存在优化的地方：
     * 1、空间的优化
     * 2、排序算法的优化
     *
     * @param array
     * @param useHash
     * @return
     * @throws NullPointerException
     */
    public static int[] checkRepeated(int[] array, boolean useHash) throws NullPointerException {
        if (array == null || array.length == 0) {
            throw new NullPointerException("array is empty");
        }
        //使用哈希算法
        if (useHash) {
            //采用动态内存
            List<Integer> repeated = new LinkedList<>();
            Map<Integer, Integer> hashMap = new HashMap<>();
            //执行一次n的空间初始化，时间复杂度n
            for (int anArray1 : array) {
                hashMap.put(anArray1, 0);
            }
            //时间复杂度为2n
            for (int anArray : array) {
                if (hashMap.get(anArray) != 0) {
                    repeated.add(anArray);
                    continue;
                }
                hashMap.put(anArray, 1);
            }
            //最坏为n/2
            int[] result = new int[repeated.size()];
            for (int i = 0; i < repeated.size(); i++) {
                result[i] = repeated.get(i);
            }
            return result;
        }
        //不使用哈希算法
        return checkRepeated(array);
    }


    public static void main(String[] args) {
        int[] a = {596, 774, 473, 868, 69, 79, 67, 162, 418, 938, 924, 607,
                481, 564, 847, 112, 572, 10, 864, 745, 754, 920, 519, 469,
                859, 805, 170, 679, 40, 529, 921, 539, 21, 512, 635, 529, 727,
                161, 6, 100, 369, 874, 113, 389, 289, 451, 672, 94, 946, 505,
                398, 516, 402, 918, 568, 6, 219, 988, 390, 198, 971, 271, 481,
                720, 311, 905, 326, 639, 248, 73, 930, 800, 836, 825, 471, 342, 397,
                977, 854, 230, 787, 476, 902, 366, 381, 668, 759, 296, 370, 535, 276,
                64, 493, 200, 22, 12, 800, 278, 207, 145, 140, 114, 535, 668, 295, 415,
                414, 528, 754, 946, 344, 585, 491, 949, 221, 269, 588, 994, 985, 819, 698,
                427, 732, 177, 464, 903, 748, 229, 253, 942, 470, 817, 707, 456, 605, 814, 341,
                32, 32, 155, 164, 831, 651, 337, 326, 772, 845, 201, 812, 329, 244, 345, 190, 83, 732,
                689, 723, 526, 783, 613, 165, 560, 964, 142, 258, 234, 265, 95, 759, 283, 847, 128, 401,
                522, 780, 577, 708, 115, 256, 510, 481, 410, 29, 247, 221, 324, 77, 54, 177, 864, 856, 479,
                273, 14, 524, 646, 796, 946, 647, 889, 243, 285, 530, 329, 672, 12, 309, 141, 365, 97, 28, 58,
                474, 690, 575, 553, 394, 821, 920, 140, 929, 53, 690, 912, 424, 272, 585, 902, 254, 412, 841
                , 420, 854, 970, 49, 649, 337, 9, 494, 43, 180, 323, 130, 146, 39, 670, 654, 497, 849, 853,
                969, 734, 783, 3, 444, 820, 125, 692, 987, 544, 92, 298, 174, 22, 706, 125, 689, 111, 467,
                269, 111, 301, 223, 734, 56, 737, 306, 221, 957, 433, 730, 677, 515, 475, 237, 259, 702, 428,
                784, 635, 704, 62, 554, 121, 61, 317, 16, 325, 987, 141, 837, 222, 506, 701, 187, 65, 694, 891,
                205, 645, 567, 822, 92, 732, 201, 547, 109, 601, 19, 489, 707, 908, 517, 595, 736, 188, 765,
                4, 235, 419, 169, 666, 421, 772, 13, 153, 979, 885, 712, 87, 536, 69, 524, 585, 222, 359, 437,
                513, 750, 125, 426, 958, 780, 133, 836, 589, 430, 864, 42, 144, 472, 665, 159, 33, 782, 262,
                823, 486, 145, 474, 294, 413, 235, 134, 813, 169, 401, 744, 642, 717, 149, 469, 60, 455, 502,
                763, 894, 346, 440, 910, 889, 979, 977, 703, 313, 660, 725, 862, 738, 590, 365, 809, 895, 194,
                66, 679, 957, 462, 967, 697, 619, 338, 648, 922, 787, 860, 619, 162, 685, 147, 71, 724, 231,
                205, 700, 316, 692, 874, 598, 293, 191, 569, 995, 137, 874, 844, 71, 756, 666, 128, 766, 391,
                990, 178, 150, 181, 751, 451, 307, 248, 644, 763, 147, 48, 453, 556, 347, 864, 461, 879, 820,
                633, 445, 41, 941, 677, 964, 234, 232, 215, 619, 565, 638, 434, 23, 103, 124, 620, 838, 982,
                361, 971, 618, 769, 982, 7, 271, 643, 737, 27, 142, 51, 345, 870, 62, 177, 979, 462, 699, 431,
                308, 266, 567, 98, 150, 363, 510, 960, 902, 723, 807, 810, 138, 661, 376, 6, 989, 398, 73, 56,
                628, 846, 971, 696, 631, 217, 666, 451, 572, 409, 613, 978, 44, 326, 666, 181, 89, 751, 979,
                574, 266, 369, 737, 738, 710, 848, 134, 799, 411, 226, 93, 571, 608, 743, 941, 782, 118, 489,
                193, 949, 739, 287, 999, 912, 544, 524, 987, 820, 491, 241, 209, 169, 935, 207, 725, 780, 448,
                576, 144, 744, 270, 819, 170, 792, 135, 419, 587, 318, 623, 782, 230, 970, 721, 317, 515, 14,
                21, 685, 401, 797, 365, 33, 759, 82, 818, 533, 972, 318, 734, 393, 678, 414, 425, 98, 173, 24,
                409, 706, 390, 606, 846, 206, 103, 562, 901, 99, 384, 427, 160, 491, 865, 41, 156, 172, 328,
                137, 193, 840, 504, 491, 772, 115, 307, 627, 583, 380, 170, 987, 302, 563, 274, 89, 353, 932,
                312, 890, 550, 977, 10, 244, 884, 823, 261, 169, 614, 898, 293, 790, 73, 772, 253, 797, 535, 703, 207, 744, 652, 187, 310, 480, 33, 264, 421, 732, 109, 562, 383, 428, 862, 520, 517, 630, 275, 602, 931, 267, 953, 994, 663, 421, 399, 574, 330, 626, 87, 141, 502, 275, 43, 132, 854, 628, 953, 16, 962, 786, 847, 190, 570, 749, 920, 173, 640, 387, 819, 838, 425, 289, 406, 740, 495, 982, 102, 180, 507, 913, 881, 702, 856, 229, 691, 772, 661, 931, 573, 114, 457, 503, 406, 866, 734, 899, 909, 627, 214, 911, 679, 311, 607, 309, 601, 646, 436, 442, 520, 499, 661, 57, 819, 331, 474, 766, 812, 613, 424, 760, 744, 164, 535, 822, 975, 764, 936, 921, 305, 658, 160, 980, 926, 668, 33, 272, 316, 634, 650, 798, 34, 425, 382, 26, 320, 70, 965, 42, 220, 859, 360, 992, 526, 92, 231, 832, 610, 169, 666, 441, 766, 883,
                694, 618, 753, 159, 253, 338, 902, 144, 983, 663, 970, 221, 193, 730, 911, 126, 461, 201, 172,
                416, 166, 308, 331, 992, 322, 625, 762, 529, 645, 660, 447, 454, 619, 878, 419, 789, 91, 192,
                70, 484, 789, 802, 87, 352, 770, 563, 501, 748, 448, 891, 320, 566, 792, 910, 190, 703, 527, 622, 582, 247, 969, 665, 203, 395, 439, 593, 147, 605, 451, 363, 17, 986, 557, 814, 331, 454, 611, 5, 398, 939, 651, 760, 900, 721, 543, 967, 188, 106, 395, 475, 45, 126, 757, 888, 78, 857, 107, 507, 739, 470, 81, 466, 770, 439, 186, 611, 153, 586, 580, 148, 251, 304, 388, 772, 687, 339, 196, 90, 569, 128, 759, 860, 728, 498, 424, 226, 872, 21, 136, 481, 151, 223, 751, 298, 632, 263, 699, 430, 451, 91, 454, 682, 638, 546, 623, 620, 258, 306, 650, 794, 979, 126, 28, 532, 402, 335, 54, 538, 819, 95, 497, 978, 585, 588, 230, 226, 686, 930, 384, 263, 780, 52, 999, 844, 163, 537, 232, 514, 930, 604, 635, 937, 859, 26, 487, 908, 163, 96, 389, 699, 580, 348, 546, 109, 306, 117, 325, 498, 847, 346, 270, 401, 37, 341, 375, 455, 453, 919, 386, 270, 688, 34, 900, 576, 415, 335, 528, 356, 587, 667, 757, 385, 324, 357, 915, 551, 572, 314, 813, 937, 836, 627, 148, 419, 418, 679, 117, 85, 20, 96, 827, 616, 995, 872, 886, 753, 44, 651, 271, 468, 730, 822, 767, 100, 273, 683, 182, 230, 541, 199, 297, 593, 563, 180, 214, 773, 637, 202, 571, 870, 401, 938, 253, 581, 432, 714, 567, 120, 342, 481, 591, 417, 547, 130, 686, 464, 124, 295, 783, 883, 35, 68, 593, 559, 570, 948, 332, 220, 937, 904, 235, 517, 303, 567, 91, 708, 662, 639, 73, 944, 400, 681, 961, 546, 94, 111, 254, 192, 247, 588, 429, 615, 370, 708, 227, 223, 631, 792, 265, 416, 913, 818, 832, 662, 9, 450, 461, 4, 808, 790, 972, 964, 799, 556, 5, 543, 419, 801, 191, 281, 468, 896, 94, 275, 265, 283, 370, 218, 992, 112, 153, 973, 480, 31, 970, 706, 945, 262, 400, 619, 516, 590, 774, 321, 553, 926, 537, 7, 506, 529, 35, 296, 381, 224, 929, 641, 204, 652, 837, 244, 154, 44, 689, 144, 784, 388, 177, 637, 342, 609, 476, 31, 413, 146, 369, 598, 317, 804, 729, 695, 501, 111, 94, 106, 580, 983, 462, 420, 929, 137, 969, 669, 376, 0, 591, 715, 223, 613, 519, 502, 858, 372, 627, 239, 275, 573, 281, 927, 179, 973, 648, 135, 140, 246, 580, 859, 382, 500, 206, 857, 810, 648, 770, 209, 816, 890, 440, 978, 831, 573, 71, 613, 492, 455, 881, 277, 154, 580, 836, 404, 173, 26, 700, 925, 90, 995, 876, 570, 19, 572, 386, 59, 441, 63, 402, 743, 413, 422, 596, 246, 616, 251, 421, 694, 235, 676, 328, 488, 45, 366, 488, 962, 264, 497, 160, 569, 229, 942, 10, 807, 187, 146, 951, 145, 629, 130, 158, 54, 803, 471, 420, 328, 906, 149, 904, 868, 770, 399, 903, 495, 403, 666, 292, 246, 328, 810, 332, 494, 990, 858, 884, 260, 648, 563, 47, 72, 307, 123, 786, 20, 295, 533, 37, 907, 373, 684, 345, 802, 805, 377, 804, 447, 935, 134, 4, 612, 979, 171, 943, 70, 352, 639, 850, 868, 449, 851, 714, 515, 843, 351, 746, 830, 940, 693, 732, 892, 120, 936, 434, 321, 939, 463, 513, 352, 923, 224, 129, 66, 641, 416, 218, 932, 762, 725, 275, 421, 580, 564, 176, 511, 457, 677, 59, 567, 967, 943, 837, 604, 779, 14, 272, 755, 912, 743, 697, 305, 293, 908, 388, 654, 400, 133, 140, 254, 579, 566, 292, 513, 274, 32, 859, 625, 53, 203, 454, 653, 614, 128, 881, 833, 531, 859, 957, 544, 195, 895, 372, 461, 135, 686, 422, 188, 737, 423, 606, 804, 771, 551, 496, 69, 32, 105, 920, 389, 850, 919, 623, 45, 57, 756, 312, 380, 672, 781, 127, 304, 56, 89, 124, 644, 441, 790, 74, 618, 53, 583, 886, 776, 245, 26, 85, 211, 952, 791, 755, 3, 726, 104, 877, 763, 349, 509, 592, 230, 940, 313, 91, 913, 580, 193, 742, 575, 53, 355, 737, 436, 992, 729, 576, 195, 167, 123, 782, 611, 516, 500, 860, 213, 656, 417, 692, 513, 29, 983, 856, 108, 978, 857, 105, 172, 673, 442, 841, 523, 504, 897, 583, 848, 909, 357, 396, 460, 689, 681, 436, 327, 558, 679, 160, 792, 261, 619, 797, 868, 564, 284, 331, 128, 854, 947, 463, 621, 952, 937, 10, 467, 866, 406, 98, 376, 403, 740, 409, 800, 270, 22, 602, 65, 945, 252, 263, 61, 294, 441, 175, 76, 135, 404, 79, 776, 705, 113, 184, 113, 557, 809, 562, 611, 874, 538, 787, 341, 738, 631, 506, 414, 284, 858, 122, 129, 609, 771, 511, 845, 831, 491, 680, 522, 385, 186, 374, 925, 186, 780, 327, 330, 689, 463, 115, 7, 928, 909, 137, 200, 366, 217, 500, 173, 880, 520, 557, 513, 222, 715, 307, 464, 222, 99, 55, 463, 971, 943, 502, 81, 479, 601, 566, 756, 576, 940, 683, 894, 135, 22, 206, 844, 83, 48, 638, 713, 657, 719, 725, 74, 984, 712, 226, 958, 207, 585, 385, 820, 634, 188, 700, 706, 10, 987, 473, 113, 659, 63, 514, 328, 650, 494, 282, 225, 120, 346,
        };
        long start = System.currentTimeMillis();
        int[] res = checkRepeated(a, true);
        System.out.println((System.currentTimeMillis() - start));
        /*for (int re : res) {
            System.err.println(re);
        }*/

    }
}
