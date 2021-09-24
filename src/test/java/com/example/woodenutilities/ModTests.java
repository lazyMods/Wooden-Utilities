package com.example.woodenutilities;

import io.github.moderngamingworlds_mods.mods.woodenutilities.common.utility.ModConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModTests {

    @Test
    @DisplayName("ModID: wooden_utilities")
    void testModId(){
        assertEquals("woodenutilities", ModConstants.MOD_ID, "The result should be wooden_utilities!");
    }
}
